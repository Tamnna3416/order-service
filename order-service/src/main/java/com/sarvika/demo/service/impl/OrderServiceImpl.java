package com.sarvika.demo.service.impl;

import com.sarvika.demo.client.ExternalServiceClient;
import com.sarvika.demo.model.dto.ProductDetails;
import com.sarvika.demo.model.dto.UserDetails;
import com.sarvika.demo.model.dto.response.OrderResponseDto;
import org.springframework.stereotype.Service;

import com.sarvika.demo.exception.OrderNotFoundException;
import com.sarvika.demo.model.Order;
import com.sarvika.demo.repository.OrderRepository;
import com.sarvika.demo.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final ExternalServiceClient externalServiceClient;

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

    public OrderResponseDto getOrderDetails(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        UserDetails user = externalServiceClient.getUserDetails(order.getUserId());

        List<ProductDetails> products = new ArrayList<>();
        for (Integer productId : order.getProductId()) {
            ProductDetails product = externalServiceClient.getProductDetails(productId);
            products.add(product);
        }

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getId());
        response.setOrderStatus(order.getStatus());
        response.setUserDetails(user);
        response.setProductDetails(products);

        return response;
    }
}

