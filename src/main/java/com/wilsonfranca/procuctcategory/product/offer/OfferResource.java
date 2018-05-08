package com.wilsonfranca.procuctcategory.product.offer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by wilson on 05/05/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfferResource extends ResourceSupport {

    @NotEmpty
    private String sku;

    private boolean available;

    @Min(1)
    private BigDecimal priceInCents;

    @Size(min = 3, max = 3)
    private String currency;

    @Min(1)
    private BigDecimal priceInEurosCents;

    @JsonSerialize(using = OfferPriceSerializer.class)
    private BigDecimal priceToDisplay;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public BigDecimal getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(BigDecimal priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BigDecimal getPriceInEurosCents() {
        return priceInEurosCents;
    }

    public void setPriceInEurosCents(BigDecimal priceInEurosCents) {
        this.priceInEurosCents = priceInEurosCents;
        setPriceToDisplay(priceInEurosCents);
    }

    public BigDecimal getPriceToDisplay() {
        return priceToDisplay;
    }

    public void setPriceToDisplay(BigDecimal priceToDisplay) {
        this.priceToDisplay = priceToDisplay;
    }
}
