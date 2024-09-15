package com.In28minutes.microservice.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {

	private static Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);
	@GetMapping("/sample-api")
//	@Retry(name = "sample-api",fallbackMethod = "fallbackMethod")
//	@CircuitBreaker(name = "default",fallbackMethod = "fallbackMethod")
	@RateLimiter(name = "default")
	public String sampleApi() {
		log.info("Hello everyone I am in the samle api");
//		ResponseEntity<String> response = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
		return "Sample API";
	}
	
	public String fallbackMethod(Exception e) {
		return "this is fallback method";
	}
}
