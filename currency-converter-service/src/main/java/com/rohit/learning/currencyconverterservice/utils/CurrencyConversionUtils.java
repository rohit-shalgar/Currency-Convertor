package com.rohit.learning.currencyconverterservice.utils;

import java.math.BigDecimal;

public interface CurrencyConversionUtils {

    static BigDecimal getTotalConvertedValue(BigDecimal conversionMultiple, int quantity) {
        return conversionMultiple.multiply(BigDecimal.valueOf(Long.valueOf(quantity)));
    }
}
