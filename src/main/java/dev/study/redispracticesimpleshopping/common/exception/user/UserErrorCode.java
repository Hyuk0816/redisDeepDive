package dev.study.redispracticesimpleshopping.common.exception.user;

import dev.study.redispracticesimpleshopping.common.exception.BusinessErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements BusinessErrorCode {
	USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND,
		"ERROR_USER_NOT_FOUND_EXCEPTION",
		"회원을 찾을 수 없습니다. 조회 ID: {email}"),
	EMAIL_DUPLICATED(HttpStatus.CONFLICT,
		"ERROR_EMAIL_DUPLICATE",
		"중복된 아이디 입니다. 중복된 아이디: {email}");


	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
