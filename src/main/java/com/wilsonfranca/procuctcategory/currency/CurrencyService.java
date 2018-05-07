package com.wilsonfranca.procuctcategory.currency;

import com.wilsonfranca.procuctcategory.currencyconverter.ConverterRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by wilson.franca on 07/05/18.
 */
@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    private ConverterRateService converterRateService;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, ConverterRateService converterRateService) {
        this.currencyRepository = currencyRepository;
        this.converterRateService = converterRateService;
    }

    public Currency findAndUpdateCurrency(final String code, final BigDecimal eurConversionFactor) {

        Currency currency = currencyRepository.findByIsoCode(code);

        if (Objects.isNull(currency)) {
            currency = new Currency();
            currency.setIsoCode(code);
            if (eurConversionFactor.longValue() > 0) {
                currency.setEurConversionFactor(eurConversionFactor);
            }
            currencyRepository.save(currency);
        }

        return currency;
    }
}
