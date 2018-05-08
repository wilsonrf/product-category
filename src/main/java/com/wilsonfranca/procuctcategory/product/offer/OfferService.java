package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.currency.Currency;
import com.wilsonfranca.procuctcategory.currency.CurrencyNotFoundException;
import com.wilsonfranca.procuctcategory.currency.CurrencyService;
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

    private CurrencyService currencyService;

    @Autowired
    public OfferService(OfferRepository offerRepository, ProductService productService,
                        CurrencyService currencyService) {
        this.offerRepository = offerRepository;
        this.productService = productService;
        this.currencyService = currencyService;
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

        // check if currency is allowed
        Currency currency = currencyService.findByCode(offerResource.getCurrency())
                .orElseThrow(CurrencyNotFoundException::new);

        Offer offer = new Offer();
        offer.setAvailable(offerResource.getAvailable());
        offer.price(currency, offerResource.getPriceInCents());
        offer.setProduct(product);
        offer.setSku(offerResource.getSku());
        Offer persisted = offerRepository.save(offer);

        return Optional.ofNullable(persisted);
    }
}
