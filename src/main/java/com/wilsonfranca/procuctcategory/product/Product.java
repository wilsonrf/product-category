package com.wilsonfranca.procuctcategory.product;

import com.wilsonfranca.procuctcategory.category.Category;
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

    @OneToOne
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

    private String title;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
