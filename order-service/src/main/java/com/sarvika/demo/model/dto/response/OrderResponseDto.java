package com.sarvika.demo.model.dto.response;

import com.sarvika.demo.model.dto.ProductDetails;
import com.sarvika.demo.model.dto.UserDetails;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class OrderResponseDto {
    private Integer orderId;
    private String orderStatus;
    private UserDetails userDetails;
    private List<ProductDetails> productDetails;
}
