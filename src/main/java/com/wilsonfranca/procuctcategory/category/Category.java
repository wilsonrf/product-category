package com.wilsonfranca.procuctcategory.category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilson.franca on 03/05/18.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> children;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(final Category parent) {
        this.parent = parent;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public void  addChildren(Category category) {
        if(getChildren() == null) {
            this.children = new ArrayList<>();
        }
        children.add(category);
    }
}
