package com.wilsonfranca.procuctcategory.currency;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wilson.franca on 07/05/18.
 */
@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Currency findByIsoCode(String code);
}
