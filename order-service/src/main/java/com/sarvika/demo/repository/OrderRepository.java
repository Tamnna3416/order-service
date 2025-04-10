package com.sarvika.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarvika.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
