package dev.study.redispracticesimpleshopping.common.exception.user;

import dev.study.redispracticesimpleshopping.common.exception.BusinessException;

public class EmailDuplicatedException extends BusinessException {
	public EmailDuplicatedException(String email) {
		super(UserErrorCode.EMAIL_DUPLICATED);
		addExtraData("email", email);
	}
}
