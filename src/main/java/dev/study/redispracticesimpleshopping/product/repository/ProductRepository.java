package dev.study.redispracticesimpleshopping.product.repository;

import dev.study.redispracticesimpleshopping.product.entity.Product;
import io.lettuce.core.dynamic.annotation.Param;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
	@Query("""
		select p from Product p
		join fetch Category c
		on p.category.id = c.id
		where p.id=:productId""")
	Optional<Product> findById(@NotNull @Param("id") Long productId);
}
