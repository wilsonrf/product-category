package com.wilsonfranca.procuctcategory.product.offer;

import com.wilsonfranca.procuctcategory.currency.CurrencyNotFoundException;
import com.wilsonfranca.procuctcategory.currency.CurrencyService;
import com.wilsonfranca.procuctcategory.product.Product;
import com.wilsonfranca.procuctcategory.product.ProductNotFoundException;
import com.wilsonfranca.procuctcategory.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by wilson on 08/05/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class OfferServiceTest {

    OfferService offerService;

    OfferRepository offerRepository;

    ProductService productService;

    CurrencyService currencyService;

    @Before
    public void setup(){
        productService = mock(ProductService.class);
        currencyService = mock(CurrencyService.class);
        offerRepository = mock(OfferRepository.class);

        offerService = new OfferService(offerRepository, productService, currencyService);
    }

    @Test(expected = ProductNotFoundException.class)
    public void test_try_to_create_an_offer_on_a_non_existing_product_and_got_product_not_found_exception() {

        when(productService.getProductById(1L)).thenReturn(Optional.empty());
        OfferResource offerResource = new OfferResource();

        offerService.createOffer(1L, offerResource);

        verify(productService, times(1)).getProductById(1L);

    }

    @Test(expected = CurrencyNotFoundException.class)
    public void test_try_to_create_an_offer_on_a_non_existing_currency_and_got_currency_not_found_exception() {

        Product product = new Product();
        product.setId(1L);

        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        when(currencyService.findByCode("EUR")).thenReturn(Optional.empty());

        OfferResource offerResource = new OfferResource();
        offerResource.setCurrency("EUR");

        offerService.createOffer(1L, offerResource);


        verify(productService, times(1)).getProductById(1L);

        verify(currencyService, times(1)).findByCode("EUR");

    }
}
