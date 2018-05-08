package com.wilsonfranca.procuctcategory.ext.fixer;

import com.wilsonfranca.procuctcategory.configuration.FixerConfigurationProperties;
import com.wilsonfranca.procuctcategory.currencyconverter.ConverterRate;
import com.wilsonfranca.procuctcategory.currencyconverter.CurrencyConverterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by wilson.franca on 08/05/18.
 */
@Service
public class FixerClient implements CurrencyConverterClient {

    private RestTemplate restTemplate;

    private FixerConfigurationProperties configurationProperties;

    @Autowired
    public FixerClient(@Qualifier("fixerRestTemplate") RestTemplate restTemplate,
                       FixerConfigurationProperties configurationProperties) {
        this.restTemplate = restTemplate;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public ConverterRate lastest(String from, String to, Double amount) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(configurationProperties.getLatestUrl())
                .queryParam("access_key", configurationProperties.getAccessKey())
                .queryParam("symbols", to)
                .queryParam("base", from);

        ResponseEntity<FixerLatestResponse> response = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
                FixerLatestResponse.class);

        FixerLatestResponse body = response.getBody();

        ConverterRate converterRate = new ConverterRate(from, to, amount, body.getRates().get(to), body.getTimestamp());

        return converterRate;
    }
}
