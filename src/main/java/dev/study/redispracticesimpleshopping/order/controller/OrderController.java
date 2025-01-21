package dev.study.redispracticesimpleshopping.order.controller;

import dev.study.redispracticesimpleshopping.common.dto.SuccessResponseDto;
import dev.study.redispracticesimpleshopping.oauth.util.UserInfo;
import dev.study.redispracticesimpleshopping.order.constants.OrderConstants;
import dev.study.redispracticesimpleshopping.order.dto.request.OrderRequestDto;
import dev.study.redispracticesimpleshopping.order.service.OrderService;
import dev.study.redispracticesimpleshopping.user.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;

	@PostMapping
	@Transactional
	public ResponseEntity<SuccessResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto,
		@UserInfo User user
		) {
		orderService.createOrder(orderRequestDto, user);
		return ResponseEntity.ok(new SuccessResponseDto(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
	}

	@PostMapping("/cart")
	public ResponseEntity<SuccessResponseDto> createOrderByCart(@RequestBody List<OrderRequestDto> orderRequestDtoList,
		@UserInfo User user) {
		orderService.createOrderByCart(orderRequestDtoList, user);
		return ResponseEntity.ok(new SuccessResponseDto(OrderConstants.STATUS_200, OrderConstants.MESSAGE_200));
	}

}
