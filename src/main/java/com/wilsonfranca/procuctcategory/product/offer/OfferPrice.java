package com.wilsonfranca.procuctcategory.product.offer;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Embeddable
public class OfferPrice implements Serializable {

    private static final long serialVersionUID = -2839902813017001296L;

    private String currency;

    private BigDecimal priceInCents;

    public OfferPrice(String currency, BigDecimal priceInCents) {
        this.currency = currency;
        this.priceInCents = priceInCents;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(BigDecimal priceInCents) {
        this.priceInCents = priceInCents;
    }
}
