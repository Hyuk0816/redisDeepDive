package dev.study.redispracticesimpleshopping.order.repository;

import dev.study.redispracticesimpleshopping.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long>, OrdersRepositoryCustom {


}
