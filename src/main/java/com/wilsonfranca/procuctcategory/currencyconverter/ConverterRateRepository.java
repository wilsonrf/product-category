package com.wilsonfranca.procuctcategory.currencyconverter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by wilson.franca on 06/05/18.
 */
public interface ConverterRateRepository extends CrudRepository<ConverterRate, Long>,
        PagingAndSortingRepository<ConverterRate, Long> {

    public Optional<ConverterRate> findFirstByFromByDateCreated(String currency);

}
