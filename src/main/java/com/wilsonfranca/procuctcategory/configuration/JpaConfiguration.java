package com.wilsonfranca.procuctcategory.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.wilsonfranca.procuctcategory.category",
        "com.wilsonfranca.procuctcategory.product", "com.wilsonfranca.procuctcategory.currency", "com.wilsonfranca.procuctcategory.currencyconverter"})
@EnableJpaAuditing
public class JpaConfiguration {
}
