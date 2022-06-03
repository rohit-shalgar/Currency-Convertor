package com.rohit.learning.currencyexchange.service;

import com.rohit.learning.currencyexchange.entity.CurrencyExchange;

import java.math.BigDecimal;

public interface CurrencyExchangeService {

    CurrencyExchange getCurrencyExchange(String fromCurrency, String toCurrency);

    CurrencyExchange setCurrencyExchange(String fromCurrency, String toCurrency, BigDecimal exchangeValue);

}
