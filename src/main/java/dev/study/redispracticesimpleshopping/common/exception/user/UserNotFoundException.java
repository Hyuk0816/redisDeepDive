package dev.study.redispracticesimpleshopping.common.exception.user;

import dev.study.redispracticesimpleshopping.common.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
	public UserNotFoundException(String email) {
		super(UserErrorCode.USER_NOT_FOUND_EXCEPTION);
		addExtraData("email", email);
	}
}
