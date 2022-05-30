package com.rohit.learning.currencyconverterservice.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class CurrencyConversion {

    @NonNull
    private int id;
    @NonNull
    private String from;
    @NonNull
    private String to;
    @NonNull
    private BigDecimal conversionMultiple;
    @NonNull
    private String environment;
    private BigDecimal convertedValue;

}


