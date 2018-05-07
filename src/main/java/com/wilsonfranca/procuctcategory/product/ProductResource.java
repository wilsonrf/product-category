package com.wilsonfranca.procuctcategory.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by wilson on 05/05/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResource extends ResourceSupport {

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @NotEmpty
    private Long category;

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

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
