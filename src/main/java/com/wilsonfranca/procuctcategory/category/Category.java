package com.wilsonfranca.procuctcategory.category;

import javax.persistence.*;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Category parent;


}
