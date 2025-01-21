package dev.study.redispracticesimpleshopping.order.service;

import dev.study.redispracticesimpleshopping.common.exception.product.ProductNotFoundException;
import dev.study.redispracticesimpleshopping.common.redis.constants.RedisConstants;
import dev.study.redispracticesimpleshopping.common.redis.service.RedisService;
import dev.study.redispracticesimpleshopping.order.dto.request.OrderRequestDto;
import dev.study.redispracticesimpleshopping.order.entity.Orders;
import dev.study.redispracticesimpleshopping.order.repository.OrdersRepository;
import dev.study.redispracticesimpleshopping.product.dto.ProductDto;
import dev.study.redispracticesimpleshopping.product.entity.Product;
import dev.study.redispracticesimpleshopping.product.repository.CategoryRepository;
import dev.study.redispracticesimpleshopping.product.repository.ProductRepository;
import dev.study.redispracticesimpleshopping.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrdersRepository ordersRepository;
	private final RedisService redisService;
	private final ProductRepository productRepository;
	private final RedisTemplate<String, ProductDto> redisTemplate;
	private final CartService cartService;
	private final CategoryRepository categoryRepository;

	public void createOrder(
		OrderRequestDto orderRequestDto,
		User user
	) {
		Product product = productRepository.findById(orderRequestDto.getProductId())
										   .orElseThrow(ProductNotFoundException::new);

		Integer totalPrice = product.getPrice() * orderRequestDto.getQuantity();

		Orders order = Orders.builder()
							 .product(product)
							 .user(user)
							 .totalPrice(totalPrice)
							 .quantity(orderRequestDto.getQuantity())
							 .pay(orderRequestDto.getPayment()
												 .name())
							 .createdAt(LocalDateTime.now())
							 .build();

		ordersRepository.save(order);
		redisService.addSaleScore(String.valueOf(product.getId()), orderRequestDto.getQuantity());
	}

	public void createOrderByCart(
		List<OrderRequestDto> orderRequestDtoList,
		User user
	) {
		List<Orders> orderList = new ArrayList<>();
		orderRequestDtoList.forEach(orderRequestDto -> {
			Product product = productRepository.findById(orderRequestDto.getProductId())
											   .orElseThrow(ProductNotFoundException::new);

			Integer totalPrice = product.getPrice() * orderRequestDto.getQuantity();

			Orders order = Orders.builder()
								 .product(product)
								 .user(user)
								 .totalPrice(totalPrice)
								 .quantity(orderRequestDto.getQuantity())
								 .pay(orderRequestDto.getPayment()
													 .name())
								 .createdAt(LocalDateTime.now())
								 .build();

			orderList.add(order);
			redisService.addSaleScore(String.valueOf(product.getId()), orderRequestDto.getQuantity());
		});
		ordersRepository.bulkInsert(orderList);
		redisTemplate.delete(RedisConstants.CART.name() + user.getEmail());
	}
}
