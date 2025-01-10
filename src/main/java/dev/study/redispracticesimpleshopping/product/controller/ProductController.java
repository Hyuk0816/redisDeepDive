package dev.study.redispracticesimpleshopping.product.controller;

import dev.study.redispracticesimpleshopping.product.dto.ProductDto;
import dev.study.redispracticesimpleshopping.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		return ResponseEntity.ok(productService.findAllProduct());
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<List<ProductDto>> getProductByCategoryId(@PathVariable Long categoryId) {
		return ResponseEntity.ok(productService.findByCategoryId(categoryId));
	}

	@GetMapping("/name")
	public ResponseEntity<List<ProductDto>> getProductByName(@RequestParam String productName) {
		return ResponseEntity.ok(productService.findByProductName(productName));
	}

}
