package dev.study.redispracticesimpleshopping.common.exception.jwt;

import dev.study.redispracticesimpleshopping.common.exception.BusinessErrorCode;
import dev.study.redispracticesimpleshopping.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class JwtException extends BusinessException {
	public JwtException(HttpStatus httpStatus,
		BusinessErrorCode businessErrorCode
		) {
		super(httpStatus,businessErrorCode);
	}
}
