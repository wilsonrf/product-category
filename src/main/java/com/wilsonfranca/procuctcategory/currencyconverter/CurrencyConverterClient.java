package com.wilsonfranca.procuctcategory.currencyconverter;

/**
 * Created by wilson.franca on 27/02/18.
 */
public interface CurrencyConverterClient {

    ConverterRate lastest(String from, String to, Double amount);

}
