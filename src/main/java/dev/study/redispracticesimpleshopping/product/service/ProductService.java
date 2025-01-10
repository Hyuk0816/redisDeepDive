package dev.study.redispracticesimpleshopping.product.service;

import dev.study.redispracticesimpleshopping.product.dto.ProductDto;
import dev.study.redispracticesimpleshopping.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<ProductDto> findAllProduct(){
		return productRepository.findAllProducts();
	}

	public List<ProductDto> findByCategoryId(Long categoryId){
		return productRepository.findByCategoryId(categoryId);
	}

	public List<ProductDto> findByProductName(String productName){
		return productRepository.findByProductName(productName);
	}

}
