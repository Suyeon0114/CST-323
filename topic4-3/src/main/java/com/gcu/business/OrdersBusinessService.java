package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.OrderEntity;
import com.gcu.data.OrdersDataService;
import com.gcu.model.OrderModel;

public class OrdersBusinessService implements OrdersBusinessServiceInterface {

	@Autowired
	public OrdersDataService service;
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from the OrdersBusinessService");
	}

	@Override
	public List<OrderModel> getOrders() {
		// Create some Orders
//		List<OrderModel> orders = new ArrayList<OrderModel>();
//		orders.add(new OrderModel(0L, "0000000001", "Product 1", 1.00f, 1));
//		orders.add(new OrderModel(1L, "0000000002", "Product 2", 2.00f, 2));
//		orders.add(new OrderModel(2L, "0000000003", "Product 3", 3.00f, 3));
//		orders.add(new OrderModel(3L, "0000000004", "Product 4", 4.00f, 4));
//		orders.add(new OrderModel(4L, "0000000005", "Product 5", 5.00f, 5));
//		
//		return orders;
		
		// Get all the Entity Orders
		List<OrderEntity> ordersEntity = service.findAll();
		
		// Iterate over the Entity Orders and create a list of Domain Orders
		List<OrderModel> ordersDomain = new ArrayList<OrderModel>();
		for(OrderEntity entity : ordersEntity) {
			ordersDomain.add(new OrderModel(entity.getId(), 
											entity.getOrderNo(), 
											entity.getProductName(), 
											entity.getPrice(), 
											entity.getQuantity()));
		}
		
		// Return list of Domain Orders
		return ordersDomain;
	}

	//@PostConstruct
	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("Calling init() method from OrderBusinessService class");
	}

	//@PreDestroy
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Calling destroy() method from OrderBusinessService class");
	}

}
