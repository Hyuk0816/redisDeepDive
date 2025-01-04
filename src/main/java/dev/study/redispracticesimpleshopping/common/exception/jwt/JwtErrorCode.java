package dev.study.redispracticesimpleshopping.common.exception.jwt;

import dev.study.redispracticesimpleshopping.common.exception.BusinessErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements BusinessErrorCode {
	INVALID_JWT_TOKEN(HttpStatus.BAD_REQUEST,
		"ERROR_INVALID_JWT_TOKEN",
		"유효하지 않은 JWT 토큰 입니다."),
	EXPIRED_JWT_TOKEN(HttpStatus.BAD_REQUEST,
		"ERROR_EXPIRED_JWT_TOKEN",
		"만료된 토큰 입니다."),
	UNSUPPORTED_JWT_TOKEN(HttpStatus.BAD_REQUEST,
		"ERROR_UNSUPPORTED_JWT_TOKEN",
		"지원되지 않은 JWT 토큰 입니다."),
	JWT_TOKEN_CLAIMS_EMPTY(HttpStatus.BAD_REQUEST,
		"ERROR_JWT_CLAIMS_EMPTY",
		"JWT 클레임이 비어있습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}

