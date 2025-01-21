package dev.study.redispracticesimpleshopping.order.controller;

import dev.study.redispracticesimpleshopping.common.dto.SuccessResponseDto;
import dev.study.redispracticesimpleshopping.oauth.util.UserInfo;
import dev.study.redispracticesimpleshopping.order.constants.OrderConstants;
import dev.study.redispracticesimpleshopping.order.service.CartService;
import dev.study.redispracticesimpleshopping.product.dto.ProductDto;
import dev.study.redispracticesimpleshopping.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	@PostMapping("/{productId}")
	public ResponseEntity<SuccessResponseDto> createCart(@PathVariable Long productId,
		@UserInfo User user
		){
		cartService.addCart(productId, user);
		return ResponseEntity.ok(new SuccessResponseDto(OrderConstants.STATUS_200, OrderConstants.MESSAGE_CART_200));
	}

	@GetMapping("/list")
	public ResponseEntity<List<ProductDto>> getCartList(@UserInfo User user){
		return ResponseEntity.ok(cartService.getCartList(user));
	}
}
