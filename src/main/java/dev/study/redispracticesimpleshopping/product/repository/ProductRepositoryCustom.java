package dev.study.redispracticesimpleshopping.product.repository;

import dev.study.redispracticesimpleshopping.product.dto.ProductDto;

import java.util.List;

public interface ProductRepositoryCustom {

	List<ProductDto> findAllProducts();

	List<ProductDto> findByCategoryId(Long categoryId);

	List<ProductDto> findByProductName(String productName);
}
