package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.currencyconverter.ConverterRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/product/{productId}/offer")
public class OfferController {

    private OfferService offerService;

    private ConverterRateService converterRateService;

    private OfferResourceAssembler offerResourceAssembler;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
        this.offerResourceAssembler = new OfferResourceAssembler();
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<PagedResources<OfferResource>> getOffers(@PathVariable Long productId, @PageableDefault  Pageable pageable,
                                                               PagedResourcesAssembler pagedResourcesAssembler) {

        Page<Offer> offers = offerService.getOffers(productId, pageable);

        if(offers.hasContent()) {
            //TODO if is not EUR, convert
            return ResponseEntity.ok(pagedResourcesAssembler.toResource(offers, new OfferResourceAssembler()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public HttpEntity<OfferResource> getOffer(@PathVariable Long productId, @PathVariable Long id) {

        Optional<Offer> optional = offerService.getOfferById(id, productId);

        return optional.filter(Objects::nonNull)
                .map(offerResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<?> createOffer(@PathVariable Long productId, @RequestBody OfferResource offerResource) {

        Optional<Offer> optional = offerService.createOffer(productId, offerResource);

        return optional.filter(Objects::nonNull)
                .map(offerResourceAssembler::toResource)
                .map( resource -> ResponseEntity
                        .created(URI.create(resource.getId().getHref()))
                        .body(resource))
                .orElse(ResponseEntity.badRequest().build());
    }
}
