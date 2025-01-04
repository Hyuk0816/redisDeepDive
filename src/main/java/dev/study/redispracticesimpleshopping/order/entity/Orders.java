package dev.study.redispracticesimpleshopping.order.entity;

import dev.study.redispracticesimpleshopping.product.entity.Product;
import dev.study.redispracticesimpleshopping.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "total_price")
	private Integer totalPrice;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "pay")
	private String pay;
}
