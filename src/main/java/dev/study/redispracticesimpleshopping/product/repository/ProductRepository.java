package dev.study.redispracticesimpleshopping.product.repository;

import dev.study.redispracticesimpleshopping.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
