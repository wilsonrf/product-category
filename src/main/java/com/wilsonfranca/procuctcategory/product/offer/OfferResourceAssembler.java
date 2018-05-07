package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.product.ProductController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by wilson on 05/05/18.
 */
public class OfferResourceAssembler extends ResourceAssemblerSupport<Offer, OfferResource> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public OfferResourceAssembler(Class<?> controllerClass, Class<OfferResource> resourceType) {
        super(controllerClass, resourceType);
    }

    public OfferResourceAssembler() {
        this(OfferController.class, OfferResource.class);
    }

    @Override
    public OfferResource toResource(Offer entity) {

        OfferResource offerResource = new OfferResource();

        Link self = ControllerLinkBuilder.linkTo(
                methodOn(OfferController.class)
                        .getOffer(entity.getProduct().getId(), entity.getId())).withSelfRel();

        offerResource.add(self);

        offerResource.setSku(entity.getSku());
        offerResource.setAvailable(entity.getAvailable());
        offerResource.setCurrency(entity.getPrice().getCurrency());
        offerResource.setPriceInCents(entity.getPrice().getPriceInCents());

        Link product = ControllerLinkBuilder.linkTo(
                methodOn(ProductController.class).getProduct(entity.getProduct().getId())).withRel("product");

        offerResource.add(product);

        return offerResource;
    }
}
