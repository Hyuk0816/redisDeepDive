package dev.study.redispracticesimpleshopping.order.repository;

import dev.study.redispracticesimpleshopping.order.entity.Orders;

import java.util.List;

public interface OrdersRepositoryCustom {

	public void bulkInsert(List<Orders> orderList);

}
