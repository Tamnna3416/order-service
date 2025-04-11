package com.sarvika.demo.client;

import com.sarvika.demo.exception.ClientServiceException;
import com.sarvika.demo.model.dto.ProductDetails;
import com.sarvika.demo.model.dto.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExternalServiceClient {

	private final RestTemplate restTemplate;

	@Value("${user-service.url}")
	private String userServiceUrl;

	@Value("${product-service.url}")
	private String productServiceUrl;

	public UserDetails getUserDetails(Integer userId) {
		String url = userServiceUrl + "/users/" + userId;
		try {
			return restTemplate.getForObject(url, UserDetails.class);
		} catch (RestClientException ex) {
			throw new ClientServiceException("Failed to fetch user information", ex);
		}
	}

	public ProductDetails getProductDetails(Integer productId) {
		String url = productServiceUrl + "/products/id/" + productId;
		try {
			return restTemplate.getForObject(url, ProductDetails.class);
		} catch (RestClientException ex) {
			throw new ClientServiceException("Failed to fetch product information", ex);
		}
	}
}
