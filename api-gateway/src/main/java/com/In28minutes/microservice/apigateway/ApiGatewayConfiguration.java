package com.In28minutes.microservice.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	public RouteLocator getRouteLocator(RouteLocatorBuilder builder) {

		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("Name", "Ravi Kumar").addRequestParameter("Village", "Thol_Distt_Kurukshetra_haryana")
								)
								
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-conversion/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-feign/**").uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-conversion-new/**")
									.filters(f -> f.rewritePath("/currency-conversion-new/", "/currency-conversion-feign/"))
									.uri("lb://currency-conversion"))
				.route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
				.build();
	}
}
