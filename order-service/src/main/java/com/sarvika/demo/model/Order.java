package com.sarvika.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "order_date", columnDefinition = "DATETIME")
	private LocalDateTime orderDate;

	private String status;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "product_id")
	private List<Integer> productId;

}
