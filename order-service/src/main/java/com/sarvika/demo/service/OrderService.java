package com.sarvika.demo.service;

import org.springframework.stereotype.Service;

import com.sarvika.demo.exception.OrderNotFoundException;
import com.sarvika.demo.model.Order;
import com.sarvika.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order saveOrder(Order order) {
        log.info("Saving order: {}", order);
        Order savedOrder = orderRepository.save(order);
        log.info("Order saved with ID: {}", savedOrder.getId());
        return savedOrder;
    }

    public List<Order> getAllOrders() {
        log.info("Fetching all orders");
        List<Order> orders = orderRepository.findAll();
        log.info("Found {} orders", orders.size());
        return orders;
    }

    public Order getOrderById(Long id) {
        log.info("Fetching order with ID: {}", id);
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            log.error("Order not found with ID: {}", id);
            throw new OrderNotFoundException("Order not found with ID: " + id);
        }
        log.info("Order found: {}", order.get());
        return order.get();
    }

    public void deleteOrder(Long id) {
        log.info("Attempting to delete order with ID: {}", id);
        if (!orderRepository.existsById(id)) {
            log.error("Order not found with ID: {}", id);
            throw new OrderNotFoundException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
        log.info("Order with ID: {} deleted successfully", id);
    }

    public Order updateOrderStatus(Long id, String status) {
        log.info("Updating order status for order ID: {} to {}", id, status);
        Order order = getOrderById(id);
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);
        log.info("Order status updated to: {}", updatedOrder.getStatus());
        return updatedOrder;
    }
}

