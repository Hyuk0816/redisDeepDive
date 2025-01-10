package dev.study.redispracticesimpleshopping.product.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.study.redispracticesimpleshopping.product.dto.ProductDto;
import jakarta.persistence.EntityManager;

import java.util.List;

import static dev.study.redispracticesimpleshopping.product.entity.QCategory.category;
import static dev.study.redispracticesimpleshopping.product.entity.QProduct.product;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public ProductRepositoryCustomImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public List<ProductDto> findAllProducts() {
		return queryFactory.select(Projections.fields(ProductDto.class,
							   product.id.as("id"),
							   product.name.as("name"),
							   product.price.as("price"),
							   product.category.name.as("category")))
						   .from(product)
						   .leftJoin(category)
						   .on(product.category.id.eq(category.id))
						   .fetch();
	}

	@Override
	public List<ProductDto> findByCategoryId(Long categoryId) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(product.category.id.eq(categoryId));

		return queryFactory.select(Projections.fields(ProductDto.class,
							   product.id.as("id"),
							   product.name.as("name"),
							   product.price.as("price"),
							   product.category.name.as("category")))
						   .from(product)
						   .where(builder)
						   .fetch();
	}

	@Override
	public List<ProductDto> findByProductName(String productName) {
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(product.name.contains(productName));

		return queryFactory.select(Projections.fields(ProductDto.class,
							   product.id.as("id"),
							   product.name.as("name"),
							   product.price.as("price"),
							   product.category.name.as("category")))
						   .from(product)
						   .where(builder)
						   .fetch();
	}
}
