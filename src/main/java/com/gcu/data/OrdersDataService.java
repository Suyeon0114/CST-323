package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.gcu.data.repository.OrdersRepository;

@Service
public class OrdersDataService implements DataAccessInterface<OrderEntity> {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	/**
	 * Non-Default constructor for constructor injection.
	 */
	public OrdersDataService(OrdersRepository ordersRepository, DataSource dataSource) {
		this.ordersRepository = ordersRepository;
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 * CRUD: finder to return all entity
	 */
	@Override
	public List<OrderEntity> findAll() {
		List<OrderEntity> orders = new ArrayList<OrderEntity>();
		
		try {
			// Get all the Entity Orders
			Iterable<OrderEntity> ordersIterable = ordersRepository.findAll();
			
			// Convert to a List and return the List
			orders = new ArrayList<OrderEntity>();
			ordersIterable.forEach(orders::add);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// Return the List
		return orders;
	}

	/**
	 * CRUD: finder to return a single entity
	 */
	@Override
	public OrderEntity findById(int id) {
		return null;
	}

	@Override
	public boolean create(OrderEntity order) {
//		try {
//			this.ordersRepository.save(order);
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
		
		// Example of "overriding" the CrudRepository save() because it simply is never called
		// You can inject a dataaSource and use the jdbcTemplate to provide a customized implementation of a save() method
		String sql = "INSERT INTO ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?, ?, ?, ?)";
		try {
			// Execute SQL Insert
			jdbcTemplateObject.update(sql, 
										order.getOrderNo(),
										order.getProductName(), 
										order.getPrice(), 
										order.getQuantity());
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(OrderEntity order) {
		String sql = "UPDATE ORDERS SET ORDER_NO = ?, PRODUCT_NAME = ?, PRICE = ?, QUANTITY = ?";
		try {
			// Execute SQL Update
			jdbcTemplateObject.update(sql, 
										order.getOrderNo(),
										order.getProductName(), 
										order.getPrice(), 
										order.getQuantity());
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(OrderEntity order) {
		String sql = "DELETE FROM ORDERS WHERE ID = ?";
		try {
			// Execute SQL Delete
			jdbcTemplateObject.update(sql, order.getOrderNo());
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
