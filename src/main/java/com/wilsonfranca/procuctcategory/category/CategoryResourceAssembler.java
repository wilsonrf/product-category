package com.wilsonfranca.procuctcategory.category;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by wilson on 05/05/18.
 */
public class CategoryResourceAssembler extends ResourceAssemblerSupport<Category, CategoryResource> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public CategoryResourceAssembler(Class<?> controllerClass, Class<CategoryResource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public CategoryResource toResource(Category entity) {

        CategoryResource categoryResource = createResourceWithId(entity.getId(), entity);

        categoryResource.setName(entity.getName());

        if(entity.getParent() != null) {
            Link parent = ControllerLinkBuilder.linkTo(methodOn(CategoryController.class).getCategory(entity.getParent().getId())).withRel("parent");
            categoryResource.add(parent);
        }

        if(entity.getChildren() != null && entity.getChildren().size() > 0) {
            Link parent = ControllerLinkBuilder.linkTo(methodOn(CategoryController.class)
                    .getCategoryChildren(entity.getId(),
                            new PageRequest(0 ,10),
                            new PagedResourcesAssembler(null, UriComponentsBuilder.fromUriString("children").build())))
                    .withRel("children");
            categoryResource.add(parent);
        }

        return categoryResource;
    }

    @Override
    public List<CategoryResource> toResources(Iterable<? extends Category> entities) {
        return super.toResources(entities);
    }
}
