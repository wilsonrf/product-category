package com.wilsonfranca.procuctcategory.product;

import com.wilsonfranca.procuctcategory.category.CategoryController;
import com.wilsonfranca.procuctcategory.product.offer.OfferController;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by wilson on 05/05/18.
 */
public class ProductResourceAssembler extends ResourceAssemblerSupport<Product, ProductResource> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public ProductResourceAssembler(Class<?> controllerClass, Class<ProductResource> resourceType) {
        super(controllerClass, resourceType);
    }

    public ProductResourceAssembler() {
        this(ProductController.class, ProductResource.class);
    }

    @Override
    public ProductResource toResource(Product entity) {

        ProductResource productResource = createResourceWithId(entity.getId(), entity);
        productResource.setTitle(entity.getTitle());
        productResource.setDescription(entity.getDescription());

        Link category = ControllerLinkBuilder.linkTo(methodOn(CategoryController.class)
                .getCategory(entity.getCategory().getId())).withRel("category");

        productResource.add(category);

        if(entity.getOffers() != null && entity.getOffers().size() > 0) {
            Link offers = ControllerLinkBuilder.linkTo(methodOn(OfferController.class)
                    .getOffers(entity.getId(), new PageRequest(0, 10), new PagedResourcesAssembler(null,
                            UriComponentsBuilder.fromUriString("product/"+entity.getId()+"offers/").build())))
                    .withRel("offers");
            productResource.add(offers);
        }

        return productResource;
    }
}
