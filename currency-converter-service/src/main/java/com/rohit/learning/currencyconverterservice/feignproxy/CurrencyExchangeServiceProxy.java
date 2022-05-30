package com.rohit.learning.currencyconverterservice.feignproxy;

import com.rohit.learning.currencyconverterservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping(path = "/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion getConversionMultiple(@PathVariable String from, @PathVariable String to);

}
