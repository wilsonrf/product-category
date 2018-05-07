package com.wilsonfranca.procuctcategory.ext.openexchangerate;

import com.wilsonfranca.procuctcategory.configuration.OpenExchangeConfigurationProperties;
import com.wilsonfranca.procuctcategory.currencyconverter.ConverterRate;
import com.wilsonfranca.procuctcategory.currencyconverter.CurrencyConverterClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Date;

/**
 * Created by wilson.franca on 27/02/18.
 */
@Service
public class OpenExchangeClient implements CurrencyConverterClient {

    private RestTemplate restTemplate;

    private OpenExchangeConfigurationProperties configurationProperties;

    @Autowired
    public OpenExchangeClient(@Qualifier("oxrRestTemplate") RestTemplate restTemplate,
                              OpenExchangeConfigurationProperties configurationProperties) {
        this.restTemplate = restTemplate;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public ConverterRate lastest(String from, String to, Double amount) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(configurationProperties.getLatestUrl())
                .queryParam("app_id", configurationProperties.getId())
                .queryParam("symbols", to)
                .queryParam("base", from);

        ResponseEntity<OpenExchangeLatestResponse> response = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
                OpenExchangeLatestResponse.class);

        OpenExchangeLatestResponse body = response.getBody();

        ConverterRate converterRate = new ConverterRate(from, to, amount, body.getRates().get(to), body.getTimestamp());

        return converterRate;
    }

    @Override
    public ConverterRate historical(String from, String to, Double amount, Instant when) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder
                .fromHttpUrl(configurationProperties.getHistoricalUrl(Date.from(when)))
                .queryParam("app_id", configurationProperties.getId())
                .queryParam("symbols", to)
                .queryParam("base", from);

        ResponseEntity<OpenExchangeLatestResponse> response = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(),
                OpenExchangeLatestResponse.class);

        OpenExchangeLatestResponse body = response.getBody();

        ConverterRate converterRate = new ConverterRate(from, to, amount, body.getRates().get(to), body.getTimestamp());

        return converterRate;
    }
}
