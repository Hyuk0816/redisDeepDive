package dev.study.redispracticesimpleshopping.order.repository;

import dev.study.redispracticesimpleshopping.order.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@RequiredArgsConstructor
public class OrdersRepositoryCustomImpl implements OrdersRepositoryCustom{
	private final JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public void bulkInsert(List<Orders> orderList){
		String sql = "INSERT INTO ordres(product_Id, user_id, total_price, pay, quantity)" +
			"VALUES(?,?,?,?,?)";

		jdbcTemplate.batchUpdate(sql, orderList,orderList.size(),
			((PreparedStatement ps, Orders order) -> {
				ps.setLong(1, order.getProduct().getId());
				ps.setLong(2,order.getUser().getId());
				ps.setDouble(3,order.getTotalPrice());
				ps.setString(4, order.getPay());
				ps.setInt(5,order.getQuantity());
			}));
	}
}
