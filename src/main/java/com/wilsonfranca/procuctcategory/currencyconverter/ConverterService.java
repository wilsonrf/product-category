package com.wilsonfranca.procuctcategory.currencyconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by wilson.franca on 06/05/18.
 */
@Service
public class ConverterService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private CurrencyConverterClient currencyConverterClient;

    private ConverterRateService converterRateService;

    @Autowired
    public ConverterService(CurrencyConverterClient currencyConverterClient,
                            ConverterRateService converterRateService) {
        this.currencyConverterClient = currencyConverterClient;
        this.converterRateService = converterRateService;
    }

    public Optional<ConverterRate> lastest(String source, String target, Double amount) {

        ConverterRate rate = null;

        try {

            rate = currencyConverterClient.lastest(source, target, amount);

            if (Objects.nonNull(rate)) {
                ConverterRate saved = converterRateService.save(rate);
                return Optional.ofNullable(saved);
            }

        } catch (Exception e) {
            logger.warn("Could not get the latest currency rate from EUR! We will try to use an outdated one!", e);
            return converterRateService.findLastRate(source);
        }

        return Optional.ofNullable(rate);
    }

    public ConverterRate save(ConverterRate converterRate) {
        return converterRateService.save(converterRate);
    }

}
