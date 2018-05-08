package com.wilsonfranca.procuctcategory.configuration;

import com.wilsonfranca.procuctcategory.currency.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by wilson on 07/05/18.
 */
@Component
public class StarttupListener implements ApplicationListener<ContextRefreshedEvent> {

    private CurrencyService currencyService;

    @Autowired
    public StarttupListener(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        currencyService.downloadRates();
    }
}
