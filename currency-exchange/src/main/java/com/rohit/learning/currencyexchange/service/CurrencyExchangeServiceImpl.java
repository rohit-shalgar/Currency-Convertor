package com.rohit.learning.currencyexchange.service;

import com.rohit.learning.currencyexchange.entity.CurrencyExchange;
import com.rohit.learning.currencyexchange.exception.CurrencyAlreadyExistsException;
import com.rohit.learning.currencyexchange.exception.CurrencyExchangeExceptionMessages;
import com.rohit.learning.currencyexchange.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Objects;

@Service
@SuppressWarnings("unused")
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Override
    public CurrencyExchange getCurrencyExchange(String fromCurrency, String toCurrency) {
        return findByFromAndTo(fromCurrency, toCurrency);
    }

    private CurrencyExchange findByFromAndTo(String fromCurrency, String toCurrency) {
        return currencyExchangeRepository.findByFromAndTo(fromCurrency, toCurrency);
    }

    @Override
    public CurrencyExchange setCurrencyExchange(String fromCurrency, String toCurrency, BigDecimal exchangeValue) {
        CurrencyExchange currencyExchange = findByFromAndTo(fromCurrency, toCurrency);
        if (Objects.isNull(currencyExchange)) {
            CurrencyExchange currencyExchangeExisting = currencyExchangeRepository.findAll()
                    .stream()
                    .max(Comparator.comparing(CurrencyExchange::getId)).get();
            currencyExchange = currencyExchangeRepository.save(
                    buildCurrency(currencyExchangeExisting, fromCurrency, toCurrency, exchangeValue));
            return currencyExchange;
        }
        throw new CurrencyAlreadyExistsException(CurrencyExchangeExceptionMessages.CURRENCY_FOUND
                + fromCurrency + ":" + toCurrency);
    }

    private CurrencyExchange buildCurrency(CurrencyExchange currencyExchangeExisting, String fromCurrency, String toCurrency, BigDecimal exchangeValue) {
        return new CurrencyExchange(currencyExchangeExisting.getId() + 1,
                fromCurrency,
                toCurrency,
                exchangeValue,
                "");
    }
}
