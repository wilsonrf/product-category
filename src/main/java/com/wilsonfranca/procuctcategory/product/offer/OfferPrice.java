package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.currency.Currency;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Embeddable
public class OfferPrice implements Serializable {

    private static final long serialVersionUID = -2839902813017001296L;

    @OneToOne
    private Currency currency;

    private BigDecimal priceInCents;

    public OfferPrice(){}

    public OfferPrice(Currency currency, BigDecimal priceInCents) {
        this.currency = currency;
        this.priceInCents = priceInCents;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(BigDecimal priceInCents) {
        this.priceInCents = priceInCents;
    }
}
