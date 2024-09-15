package com.In28minutes.microservice.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter {
	private static Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		 log.info("Requst is passing from API Gateway: {}",exchange.getRequest().getURI());
		return chain.filter(exchange);
	}
}
