package dev.study.redispracticesimpleshopping.order.service;

import dev.study.redispracticesimpleshopping.common.exception.product.ProductNotFoundException;
import dev.study.redispracticesimpleshopping.common.redis.constants.RedisConstants;
import dev.study.redispracticesimpleshopping.product.dto.ProductDto;
import dev.study.redispracticesimpleshopping.product.entity.Product;
import dev.study.redispracticesimpleshopping.product.repository.ProductRepository;
import dev.study.redispracticesimpleshopping.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
	private final ProductRepository productRepository;
	private final RedisTemplate<String, ProductDto> redisTemplate;

	public void addCart(
		Long productId,
		User user
	) {
		Product product = productRepository.findById(productId)
										   .orElseThrow(ProductNotFoundException::new);

		ProductDto productDto = ProductDto.builder()
										  .id(product.getId())
										  .name(product.getName())
										  .price(product.getPrice())
										  .category(String.valueOf(product.getCategory()))
										  .build();

		redisTemplate.opsForList()
					 .rightPush(RedisConstants.CART.name() + user.getEmail(), productDto);

	}

	public List<ProductDto> getCartList(User user) {
		return redisTemplate.opsForList()
							.range(RedisConstants.CART.name() + user.getEmail(), 0, -1);

	}

}
