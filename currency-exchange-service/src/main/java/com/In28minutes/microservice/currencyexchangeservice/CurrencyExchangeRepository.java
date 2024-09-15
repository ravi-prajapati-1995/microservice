package com.In28minutes.microservice.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	CurrencyExchange findOneByFromAndTo(String from,String to);
}
