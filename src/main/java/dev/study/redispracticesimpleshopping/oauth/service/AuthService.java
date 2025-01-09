package dev.study.redispracticesimpleshopping.oauth.service;

import dev.study.redispracticesimpleshopping.common.exception.user.UserNotFoundException;
import dev.study.redispracticesimpleshopping.common.jwt.dto.AccessTokenResponse;
import dev.study.redispracticesimpleshopping.common.jwt.service.TokenProvider;
import dev.study.redispracticesimpleshopping.oauth.constants.AuthConstants;
import dev.study.redispracticesimpleshopping.oauth.dto.LoginDto;
import dev.study.redispracticesimpleshopping.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {

	private static final long maxAgeForCookie = 7 * 24 * 60 * 60;
	private final AuthenticationManager authenticationManager;
	private final TokenProvider tokenProvider;
	private final RedisTemplate<String, String> redisTemplate;
	private final UserRepository usersRepository;

	public void login(LoginDto loginDto, HttpServletResponse response){
		usersRepository.findByEmail(loginDto.getEmail())
			.orElseThrow(() -> new UserNotFoundException(loginDto.getEmail()));

		Authentication authentication;

		try{
			authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
			);
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String accessToken = tokenProvider.createAccessToken(authentication);
			String refreshToken = tokenProvider.createRefreshToken(authentication);

			response.addHeader("Authorization", "Bearer " + accessToken);
			tokenProvider.createRefreshTokenCookie(response,"refreshToken",refreshToken,maxAgeForCookie);

		}catch (BadCredentialsException e){
			throw new BadCredentialsException(AuthConstants.MESSAGE_401);
		}
	}

	public void logout(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			Optional<String> refreshCookieValue = Arrays.stream(cookies)
										   .filter(cookie -> "refreshToken".equals(cookie.getName()) && hasText(cookie.getValue()))
										   .map(Cookie::getValue)
										   .findFirst();

			refreshCookieValue.ifPresent(value -> {
				tokenProvider.deleteRefreshTokenCookie(response, value);
				redisTemplate.delete(value);
			});
		}
	}

	public AccessTokenResponse getAccessToken(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			Optional<String> cookieValue = Arrays.stream(cookies)
												 .filter(c -> "refreshToken".equals(c.getName()) && hasText(c.getValue()))
												 .map(Cookie::getValue)
												 .findFirst();

			if (cookieValue.isPresent()) {
				log.info(cookieValue.get());
				String accessToken = tokenProvider.searchAccessTokenByRefreshToken(cookieValue.get());
				return AccessTokenResponse.builder()
										  .accessToken(accessToken)
										  .type("bearer")
										  .build();
			}
		}
		return null;
	}

}
