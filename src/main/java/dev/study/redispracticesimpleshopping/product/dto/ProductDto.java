package dev.study.redispracticesimpleshopping.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Long id;
	private String name;
	private Integer price;
	private String category;

}
