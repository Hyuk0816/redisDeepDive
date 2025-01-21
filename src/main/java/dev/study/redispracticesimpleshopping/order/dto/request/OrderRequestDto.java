package dev.study.redispracticesimpleshopping.order.dto.request;

import dev.study.redispracticesimpleshopping.pg.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
	private Long productId;
	private Integer quantity;
	private Payment payment;
}
