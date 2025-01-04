package dev.study.redispracticesimpleshopping.coupon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "coupon")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coupon_id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "discount_rate")
	private Double discountRate;

	@Column(name = "expiration_date")
	private LocalDateTime expirationDate;
}
