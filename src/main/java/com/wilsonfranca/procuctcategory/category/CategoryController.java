package com.wilsonfranca.procuctcategory.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by wilson on 05/05/18.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    private CategoryResourceAssembler categoryResourceAssembler;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
        categoryResourceAssembler = new CategoryResourceAssembler(CategoryController.class, CategoryResource.class);
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<PagedResources<CategoryResource>> getCategories(Pageable pageable, PagedResourcesAssembler assembler) {

        Page<Category> categories = categoryService.getCategories(pageable);

        if (categories.hasContent()) {

            return ResponseEntity.ok(assembler.toResource(categories, new CategoryResourceAssembler(CategoryController.class, CategoryResource.class)));
        } else  {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public HttpEntity<CategoryResource> getCategory(@PathVariable Long id) {

        Optional<Category> optional = categoryService.getCategoryById(id);

        return optional.filter(Objects::nonNull)
                .map(categoryResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<?> createCategory(@RequestBody CategoryResource categoryResource) {

        Optional<Category> optional = categoryService.createCategory(categoryResource);

        return optional.filter(Objects::nonNull)
                .map(categoryResourceAssembler::toResource)
                .map( resource -> ResponseEntity
                        .created(URI.create(resource.getId().getHref()))
                        .body(resource))
                .orElse(ResponseEntity.badRequest().build());
    }

    @RequestMapping(value = "{id}/children", method = RequestMethod.GET)
    public HttpEntity<PagedResources<CategoryResource>> getCategoryChildren(@PathVariable Long id, Pageable pageable,
                                                                            PagedResourcesAssembler assembler) {

        Page<Category> categories = categoryService.getCategoryChildren(id, pageable);

        if (categories.hasContent()) {

            return ResponseEntity.ok(assembler.toResource(categories,
                    new CategoryResourceAssembler(CategoryController.class, CategoryResource.class)));
        } else  {
            return ResponseEntity.notFound().build();
        }

    }

    @RequestMapping(value = "{id}/children", method = RequestMethod.POST)
    public HttpEntity<?> createCategoryChildren(@PathVariable Long id, @RequestBody CategoryResource categoryResource) {

        Optional<Category> optional = categoryService.createCategoryChildren(id, categoryResource);

        return optional.filter(Objects::nonNull)
                .map(categoryResourceAssembler::toResource)
                .map( resource -> ResponseEntity
                        .created(URI.create(resource.getId().getHref()))
                        .body(resource))
                .orElse(ResponseEntity.badRequest().build());
    }

}
