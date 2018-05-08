package com.wilsonfranca.procuctcategory.currency;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by wilson.franca on 07/05/18.
 */
@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Optional<Currency> findByIsoCode(String code);
    Stream<Currency> readAllByIsoCodeNotNull();

}
