package dev.study.redispracticesimpleshopping.common.exception.product;

import dev.study.redispracticesimpleshopping.common.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {

	public ProductNotFoundException() {super(ProductErrorCode.PRODUCT_NOT_FOUND);}
}
