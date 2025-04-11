package com.sarvika.demo.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDetails {
	private Long id;
	private String name;
	private String email;
}
