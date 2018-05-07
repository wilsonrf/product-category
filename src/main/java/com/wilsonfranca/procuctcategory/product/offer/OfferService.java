package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.product.Product;
import com.wilsonfranca.procuctcategory.product.ProductNotFoundException;
import com.wilsonfranca.procuctcategory.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by wilson on 05/05/18.
 */
@Service
public class OfferService {

    private OfferRepository offerRepository;

    private ProductService productService;

    @Autowired
    public OfferService(OfferRepository offerRepository, ProductService productService) {
        this.offerRepository = offerRepository;
        this.productService = productService;
    }

    public Page<Offer> getOffers(Long productId, Pageable pageable) {
     return offerRepository.findAllByProductId(productId, pageable);
    }

    public Optional<Offer> getOfferById(Long id, Long productId) {
        return offerRepository.findByIdAndProductId(id, productId);
    }

    public Optional<Offer> createOffer(Long productId, OfferResource offerResource) {

        Optional<Product> optional = productService.getProductById(productId);
        Product product = optional.filter(Objects::nonNull)
                .orElseThrow(ProductNotFoundException::new);
        Offer offer = new Offer();
        offer.setAvailable(offerResource.getAvailable());
        offer.price(offerResource.getCurrency(), offerResource.getPriceInCents());
        offer.setProduct(product);
        offer.setSku(offerResource.getSku());
        Offer persisted = offerRepository.save(offer);

        return Optional.ofNullable(persisted);
    }
}
