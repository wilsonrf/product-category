package com.wilsonfranca.procuctcategory.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

/**
 * Created by wilson on 05/05/18.
 */
@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class HateoasConfiguration {
}
