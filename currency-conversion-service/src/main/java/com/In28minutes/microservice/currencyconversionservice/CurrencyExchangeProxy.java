package com.In28minutes.microservice.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion exchangeCurrent(@PathVariable String from,@PathVariable String to);
}
