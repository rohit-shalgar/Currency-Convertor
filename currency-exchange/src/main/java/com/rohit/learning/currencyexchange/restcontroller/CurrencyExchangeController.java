package com.rohit.learning.currencyexchange.restcontroller;

import com.rohit.learning.currencyexchange.entity.CurrencyExchange;
import com.rohit.learning.currencyexchange.exception.CurrencyAlreadyExistsException;
import com.rohit.learning.currencyexchange.exception.CurrencyExchangeExceptionMessages;
import com.rohit.learning.currencyexchange.exception.CurrencyExchangePairNotFoundException;
import com.rohit.learning.currencyexchange.service.CurrencyExchangeService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@RestController
@SuppressWarnings("unused")
public class CurrencyExchangeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange getConversionMultiple(@PathVariable String from, @PathVariable String to) {
        val currencyExchange = currencyExchangeService.getCurrencyExchange(from, to);
        if (Objects.nonNull(currencyExchange)) {
            currencyExchange.setEnvironment(buildEnvironment());
            return currencyExchange;
        }
        throw new CurrencyExchangePairNotFoundException(CurrencyExchangeExceptionMessages.CURRENCY_NOT_FOUND + from + ":" + to);
    }

    private String buildEnvironment() {
        return environment.getProperty("local.server.port");
    }

    @PostMapping(path = "/currency-exchange/from/{from}/to/{to}/exchangeRate/{exchangeRate}")
    //@Retry(name = "setCurrencyExchange", fallbackMethod = "currencyAlreadyExistsResponseHandler")
   // @CircuitBreaker(name = "default",fallbackMethod = "currencyAlreadyExistsResponseHandler")
    @RateLimiter(name = "default")
    //@Bulkhead(name = "default")
    public CurrencyExchange setConversionMultiple(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal exchangeRate
    ) {
        return currencyExchangeService.setCurrencyExchange(from, to, exchangeRate);
    }

    @SuppressWarnings("rawtypes")
    private ResponseEntity currencyAlreadyExistsResponseHandler(CurrencyAlreadyExistsException exception) {
        LOGGER.error(exception.getMessage());
        return ResponseEntity.ok("Currency already exists, please use - /currency-exchange/from/{from}/to/{to} to get the response");
    }
}
