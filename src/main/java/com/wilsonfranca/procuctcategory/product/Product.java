package com.wilsonfranca.procuctcategory.product;

import com.wilsonfranca.procuctcategory.product.offer.Offer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 3814459904845798796L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

    private String title;

    private String description;


}
