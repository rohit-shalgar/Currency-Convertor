package com.rohit.learning.currencyconverterservice.controller;

import com.rohit.learning.currencyconverterservice.feignproxy.CurrencyExchangeServiceProxy;
import com.rohit.learning.currencyconverterservice.model.CurrencyConversion;
import com.rohit.learning.currencyconverterservice.utils.CurrencyConversionUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceProxy currencyExchangeProxy;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{qty}")
    public CurrencyConversion getCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable int qty
    ) {
        val currencyConversion = currencyExchangeProxy.getConversionMultiple(from, to);
        BigDecimal conversionMultiple = currencyConversion.getConversionMultiple();
        BigDecimal convertedTotalValue = CurrencyConversionUtils.getTotalConvertedValue(
                conversionMultiple, qty);
        currencyConversion.setConvertedValue(convertedTotalValue);
        return currencyConversion;
    }
}
