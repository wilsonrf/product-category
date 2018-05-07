package com.wilsonfranca.procuctcategory.currencyconverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by wilson.franca on 06/05/18.
 */
@Service
public class ConverterRateService {

    private ConverterRateRepository converterRateRepository;

    @Autowired
    public ConverterRateService(ConverterRateRepository converterRateRepository) {
        this.converterRateRepository = converterRateRepository;
    }

    public ConverterRate save(ConverterRate converterRate) {
        return converterRateRepository.save(converterRate);
    }

    public Optional<ConverterRate> findLastRate(String currency) {
        return converterRateRepository.findFirstByFromByDateCreated(currency);
    }
}
