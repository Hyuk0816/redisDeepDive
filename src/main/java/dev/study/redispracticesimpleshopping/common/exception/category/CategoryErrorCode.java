package dev.study.redispracticesimpleshopping.common.exception.category;

import dev.study.redispracticesimpleshopping.common.exception.BusinessErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum CategoryErrorCode implements BusinessErrorCode {
	CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,
		"ERROR_CATEGORY_NOT_FOUND",
		"카테고리를 찾을 수 없습니다");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
