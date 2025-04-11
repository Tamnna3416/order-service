package com.sarvika.demo.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductDetails {
	private String id;
	private String name;
	private String description;
	private double price;
	private String category;

}
