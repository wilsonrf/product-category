package com.wilsonfranca.procuctcategory.currency;

import com.wilsonfranca.procuctcategory.currencyconverter.ConverterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by wilson.franca on 07/05/18.
 */
@Service
public class CurrencyService {

    public static final String EUR = "EUR";
    public static final double AMOUNT = 1.0;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private CurrencyRepository currencyRepository;

    private ConverterService converterService;

    @Autowired
    public CurrencyService(CurrencyRepository currencyRepository, ConverterService converterService) {
        this.currencyRepository = currencyRepository;
        this.converterService = converterService;
    }

    public Optional<Currency> findByCode(final String code) {
        return currencyRepository.findByIsoCode(code);
    }

    public Currency findAndUpdateCurrency(final String code, final BigDecimal eurConversionFactor) {

        Currency currency = currencyRepository.findByIsoCode(code)
                .filter(Objects::nonNull)
                .orElseGet(Currency::new);
        currency.setIsoCode(code);
        currency.setEurConversionFactor(eurConversionFactor);
        currencyRepository.save(currency);
        return currency;
    }

    @Transactional
    @Scheduled(cron = "0 0 * ? * * *")
    public void downloadRates() {
        logger.info("Starting to download rates");
        Stream<Currency> stream = currencyRepository.readAllByIsoCodeNotNull();
        stream.forEach(currency -> {
            converterService
                    .lastest(currency.getIsoCode(), EUR, AMOUNT)
                    .ifPresent(converterRate -> this.findAndUpdateCurrency(converterRate.getSource(),
                            BigDecimal.valueOf(converterRate.getRate())));
        });
        logger.info("Finish to download rates");

    }
}
