package com.wilsonfranca.procuctcategory.ext.currencyconverterapi;

import com.wilsonfranca.procuctcategory.configuration.CurrencyConverterApiConfigurationProperties;
import com.wilsonfranca.procuctcategory.currencyconverter.ConverterRate;
import com.wilsonfranca.procuctcategory.currencyconverter.CurrencyConverterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Map;

/**
 * Created by wilson on 08/05/18.
 */
@Service
public class CurrencyConverterApiClient implements CurrencyConverterClient {

    private RestTemplate restTemplate;

    private CurrencyConverterApiConfigurationProperties configurationProperties;


    @Autowired
    public CurrencyConverterApiClient(@Qualifier("ccaRestTemplate") RestTemplate restTemplate,
                                      CurrencyConverterApiConfigurationProperties configurationProperties) {
        this.restTemplate = restTemplate;
        this.configurationProperties = configurationProperties;

    }

    @Override
    public ConverterRate lastest(String from, String to, Double amount) {

        final String query = from + "_" + to;

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(configurationProperties.getLatestUrl())
                .queryParam("q", query)
                .queryParam("compact", "ultra");

        ResponseEntity<Map>  responseEntity =  restTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
                Map.class);

        Map<String, Double> body = responseEntity.getBody();

        ConverterRate converterRate = new ConverterRate(from, to, 1d, body.get(query), Instant.now().getEpochSecond());

        return converterRate;
    }
}
