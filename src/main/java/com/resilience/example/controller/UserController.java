package com.resilience.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class UserController {
	
	private static final String USER_SERVICE ="userService";
	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping("/user")
	@CircuitBreaker(name="USER_SERVICE",fallbackMethod="orderFallback")
	public String createOrder() {
		
		String response = restTemplate.getForObject("http://localhost:8086/account/user", String.class);
		return response;
	}

	public String orderFallback(Exception e) {
		
		return "service is not responding";
		
	}
	
	
}
