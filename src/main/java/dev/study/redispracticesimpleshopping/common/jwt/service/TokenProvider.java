package dev.study.redispracticesimpleshopping.common.jwt.service;

import dev.study.redispracticesimpleshopping.common.exception.jwt.JwtErrorCode;
import dev.study.redispracticesimpleshopping.common.exception.jwt.JwtException;
import dev.study.redispracticesimpleshopping.common.exception.user.UserNotFoundException;
import dev.study.redispracticesimpleshopping.user.entity.User;
import dev.study.redispracticesimpleshopping.user.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenProvider {

	@Value("${app.jwt-secret}")
	private String secretKey;
	@Value("${app.jwt-expiration}")
	private Long accessTokenValid;

	private final RedisTemplate<String, String> redisTemplate;

	private final UserRepository userRepository;

	public SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(this.secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateAccessToken(Authentication authentication) {
		Date now = new Date();
		Date accessValidate = new Date(now.getTime() + accessTokenValid);
		return Jwts.builder()
				   .claim("email", authentication.getName())
				   .claim("role", authentication.getAuthorities())
				   .issuedAt(now)
				   .expiration(accessValidate)
				   .signWith(getSignKey())
				   .compact();
	}

	public String createAccessToken(Authentication authentication) {
		User user = userRepository.findByEmail(authentication.getName())
								  .orElseThrow(() -> new UserNotFoundException(authentication.getName()));
		String accessToken = this.generateAccessToken(authentication);
		redisTemplate.opsForValue()
					 .set(user.getEmail(), accessToken, Duration.ofHours(1));
		return accessToken;
	}

	public String createRefreshToken(Authentication authentication) {
		UUID refreshTokenUUID = UUID.randomUUID();
		redisTemplate.opsForValue()
					 .set(String.valueOf(refreshTokenUUID), authentication.getName(), Duration.ofDays(1));
		return String.valueOf(refreshTokenUUID);
	}

	public String searchAccessTokenByRefreshToken(String refreshToken) {
		String email = redisTemplate.opsForValue()
									.get(refreshToken);
		if (hasText(email)) {
			String accessToken = redisTemplate.opsForValue()
											  .get(email);
			if (hasText(accessToken)) {
				return accessToken;
			} else {
				redisTemplate.delete(refreshToken);
			}

		} else {
			redisTemplate.delete(refreshToken);
		}
		return null;
	}

	public String getEmailFromToken(String token) {
		Claims claims = Jwts.parser()
							.verifyWith(getSignKey())
							.build()
							.parseSignedClaims(token)
							.getPayload();
		return String.valueOf(claims.get("email"));
	}
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) {
		try {
			Claims claims = Jwts.parser()
								.verifyWith(getSignKey())
								.build()
								.parseSignedClaims(token)
								.getPayload();
			Date now = new Date();
			return claims.getExpiration().after(now);
		} catch (MalformedJwtException ex) {
			throw new JwtException(JwtErrorCode.INVALID_JWT_TOKEN.getHttpStatus(), JwtErrorCode.INVALID_JWT_TOKEN);
		} catch (ExpiredJwtException ex) {
			throw new JwtException(JwtErrorCode.EXPIRED_JWT_TOKEN.getHttpStatus(), JwtErrorCode.EXPIRED_JWT_TOKEN);
		} catch (UnsupportedJwtException ex) {
			throw new JwtException(JwtErrorCode.UNSUPPORTED_JWT_TOKEN.getHttpStatus(), JwtErrorCode.UNSUPPORTED_JWT_TOKEN);
		} catch (IllegalArgumentException ex) {
			throw new JwtException(JwtErrorCode.JWT_TOKEN_CLAIMS_EMPTY.getHttpStatus(), JwtErrorCode.JWT_TOKEN_CLAIMS_EMPTY);
		}
	}

	public void createRefreshTokenCookie(HttpServletResponse response, String refreshTokenKey, String refreshTokenValue, long maxAgeForCookie) {
		ResponseCookie cookie = ResponseCookie.from(refreshTokenKey, refreshTokenValue)
											  .path("/")
											  .httpOnly(true)
											  .maxAge(maxAgeForCookie)
											  .sameSite("Strict")
											  .secure(true)
											  .build();
		response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
	}

	public void deleteRefreshTokenCookie(HttpServletResponse response, String refreshTokenKey) {
		createRefreshTokenCookie(response, refreshTokenKey, "", 0);
	}
}

