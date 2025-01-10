package dev.study.redispracticesimpleshopping.common.redis.service;

import dev.study.redispracticesimpleshopping.common.redis.constants.RedisConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

	private final RedisTemplate<String, String> redisTemplate;

	private final StringRedisTemplate stringRedisTemplate;

	public void addSaleScore(String productId) {
		Double score =  redisTemplate.opsForZSet().score(RedisConstants.PRODUCT_SALES.name(), productId);

		if(score == null) {
			redisTemplate.opsForZSet().add(RedisConstants.PRODUCT_SALES.name(), productId, 0);
		}else{
			redisTemplate.opsForZSet().incrementScore(RedisConstants.PRODUCT_SALES.name(), productId, 1);
		}
	}
}
