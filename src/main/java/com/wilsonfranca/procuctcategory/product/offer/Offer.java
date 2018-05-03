package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.product.Product;

import javax.persistence.*;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private OfferPrice price;



}
