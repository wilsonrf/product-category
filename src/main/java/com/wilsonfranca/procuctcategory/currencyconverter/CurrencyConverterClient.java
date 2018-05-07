package com.wilsonfranca.procuctcategory.currencyconverter;

import java.time.Instant;

/**
 * Created by wilson.franca on 27/02/18.
 */
public interface CurrencyConverterClient {

    ConverterRate lastest(String from, String to, Double amount);

    ConverterRate historical(String from, String to, Double amount, Instant when);

}
