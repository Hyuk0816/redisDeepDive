package dev.study.redispracticesimpleshopping.common.redis.service;

import dev.study.redispracticesimpleshopping.common.redis.constants.RedisConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RedisService {

	private final RedisTemplate<String, String> redisTemplate;


	public void addSaleScore(
		String productId,
		int quantity
	) {
		Double allScore = redisTemplate.opsForZSet()
									   .score(RedisConstants.PRODUCT_SALES.name(), productId);

		if (allScore == null) {
			redisTemplate.opsForZSet()
						 .add(RedisConstants.PRODUCT_SALES.name(), productId, quantity);
			redisTemplate.opsForZSet()
						 .add(RedisConstants.MONTHLY_SALES.name() + "_" + LocalDateTime.now()
																					   .getMonth(),
							 productId,
							 quantity);
		} else {
			redisTemplate.opsForZSet()
						 .incrementScore(RedisConstants.PRODUCT_SALES.name(), productId, quantity);
			redisTemplate.opsForZSet()
						 .incrementScore(RedisConstants.MONTHLY_SALES.name() + "_" + LocalDateTime.now()
																								  .getMonth(),
							 productId,
							 quantity);
		}
	}
}
