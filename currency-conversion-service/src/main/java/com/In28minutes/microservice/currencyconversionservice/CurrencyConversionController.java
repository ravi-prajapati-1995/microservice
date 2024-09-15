package com.In28minutes.microservice.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		Map<String,String> map = new HashMap<>();
		map.put("from",from);
		map.put("to", to);
		
		ResponseEntity<CurrencyConversion> response =  new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/USD/to/INR",CurrencyConversion.class,map);		
		CurrencyConversion cc = response.getBody();
		return new CurrencyConversion(cc.getId(), from, to, cc.getConversionMultiple(), quantity, quantity.multiply(cc.getConversionMultiple()),cc.getPort());
	}
	
	
	@GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversion cc = proxy.exchangeCurrent(from, to);
		return new CurrencyConversion(cc.getId(), from, to, cc.getConversionMultiple(), quantity, quantity.multiply(cc.getConversionMultiple()),cc.getPort()+"From Feign");
	}
}
