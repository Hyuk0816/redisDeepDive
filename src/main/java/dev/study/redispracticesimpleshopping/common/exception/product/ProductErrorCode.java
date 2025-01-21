package dev.study.redispracticesimpleshopping.common.exception.product;

import dev.study.redispracticesimpleshopping.common.exception.BusinessErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum ProductErrorCode implements BusinessErrorCode {
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,
		"ERROR_PRODUCT_NOT_FOUND",
		"해당 상품이 없습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
