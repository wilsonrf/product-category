package com.wilsonfranca.procuctcategory.configuration;

import com.wilsonfranca.procuctcategory.ext.fixer.FixerErrorHandler;
import com.wilsonfranca.procuctcategory.ext.openexchangerate.OpenExchangeErrorHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean(name = "oxrRestTemplate")
    RestTemplate oxrRestTemplate() {
        OpenExchangeErrorHandler errorHandler = new OpenExchangeErrorHandler();
        RestTemplate restTemplate = new RestTemplate(getRequestFactory());
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate;
    }

    @Bean(name = "fixerRestTemplate")
    RestTemplate fixerRestemplate() {
        FixerErrorHandler errorHandler = new FixerErrorHandler();
        RestTemplate restTemplate = new RestTemplate(getRequestFactory());
        restTemplate.setErrorHandler(errorHandler);
        return restTemplate;
    }

    @Bean(name = "ccaRestTemplate")
    RestTemplate ccaRestemplate() {
        RestTemplate restTemplate = new RestTemplate(getRequestFactory());
        return restTemplate;
    }

    private ClientHttpRequestFactory getRequestFactory(){
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .setConnectTimeout(3000)
                .setSocketTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .build();

        HttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}
