package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private Boolean available;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private OfferPrice price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OfferPrice getPrice() {
        return price;
    }

    public void setPrice(OfferPrice price) {
        this.price = price;
    }

    public void price(String currency, BigDecimal priceInCents) {
        OfferPrice offerPrice = new OfferPrice(currency, priceInCents);
        this.setPrice(offerPrice);
    }
}
