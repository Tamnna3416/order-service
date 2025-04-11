package com.sarvika.demo.service;

import java.util.List;

import com.sarvika.demo.model.Order;
import com.sarvika.demo.model.dto.response.OrderResponseDto;

public interface OrderService {
	Order saveOrder(Order order);

	List<Order> getAllOrders();

	Order getOrderById(Long id);

	void deleteOrder(Long id);

	OrderResponseDto getOrderDetails(Long orderId);

}
