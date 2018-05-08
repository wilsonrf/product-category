package com.wilsonfranca.procuctcategory.configuration;

import com.wilsonfranca.procuctcategory.currencyconverter.CurrencyConverterClient;
import com.wilsonfranca.procuctcategory.ext.fixer.FixerClient;
import com.wilsonfranca.procuctcategory.ext.openexchangerate.OpenExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wilson.franca on 08/05/18.
 */
@Configuration
public class CurrencyConverterClientConfiguration {

    @Autowired
    OpenExchangeClient openExchangeClient;

    @Autowired
    FixerClient fixerClient;

    @Bean(name = "currencyConverterClient")
    @Conditional(OpenExchangeCondition.class)
    public CurrencyConverterClient openExchange() {
        return openExchangeClient;
    }

    @Bean(name = "currencyConverterClient")
    @Conditional(FixerCondition.class)
    public CurrencyConverterClient fixer() { return fixerClient; }

}
