package com.sarvika.demo.controller;

import com.sarvika.demo.model.dto.response.OrderResponseDto;
import com.sarvika.demo.service.impl.OrderServiceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sarvika.demo.model.Order;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderServiceImpl orderService;

	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		Order savedOrder = orderService.saveOrder(order);
		return ResponseEntity.ok(savedOrder);
	}

	@GetMapping
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Order order = orderService.getOrderById(id);
		return ResponseEntity.ok(order);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable Integer id, @RequestBody Order order) {
		order.setId(id);
		Order updatedOrder = orderService.saveOrder(order);
		return ResponseEntity.ok(updatedOrder);
	}

	@GetMapping("/details/{id}")
	public ResponseEntity<OrderResponseDto> getOrderDetailsById(@PathVariable Long id) {
		OrderResponseDto order = orderService.getOrderDetails(id);
		return ResponseEntity.ok(order);
	}
}
