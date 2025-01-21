package dev.study.redispracticesimpleshopping.common.exception.category;

import dev.study.redispracticesimpleshopping.common.exception.BusinessException;

public class CategoryNotFoundException extends BusinessException {
	public CategoryNotFoundException() {super(CategoryErrorCode.CATEGORY_NOT_FOUND);}
}
