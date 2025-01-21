package dev.study.redispracticesimpleshopping.product.repository;

import dev.study.redispracticesimpleshopping.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findByName(String name);

}
