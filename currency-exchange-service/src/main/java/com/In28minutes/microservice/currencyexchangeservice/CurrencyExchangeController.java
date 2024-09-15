package com.In28minutes.microservice.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	static Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repo;
	
	@GetMapping("/")
	public String test() {
		return "Hello world from : "+environment.getProperty("local.server.port");
	}

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange exchangeCurrent(@PathVariable String from,@PathVariable String to) {
		log.info("currency exchange is called from: {} and to: {}",from ,to);
		CurrencyExchange currencyExchange = repo.findOneByFromAndTo(from, to);
		if(currencyExchange ==null) {
			throw new RuntimeException(String.format("Currency exchange not found from : %s to : %s", from,to));
		}
		
		currencyExchange.setPort(environment.getProperty("local.server.port"));
		return currencyExchange;
	}
}
