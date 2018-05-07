package com.wilsonfranca.procuctcategory.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.constraints.NotNull;

/**
 * Created by wilson on 05/05/18.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryResource extends ResourceSupport {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
