package com.wilsonfranca.procuctcategory.product;

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
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private ProductResourceAssembler productResourceAssembler;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
        this.productResourceAssembler = new ProductResourceAssembler();
    }


    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<PagedResources<ProductResource>> getProducts(@PageableDefault Pageable pageable,
                                                                   PagedResourcesAssembler pagedResourcesAssembler) {

        Page<Product> products = productService.getProducts(pageable);

        if(products.hasContent()) {
            return ResponseEntity.ok(pagedResourcesAssembler.toResource(products, new ProductResourceAssembler()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public HttpEntity<ProductResource> getProduct(@PathVariable Long id) {

        Optional<Product> optional = productService.getProductById(id);

        return optional.filter(Objects::nonNull)
                .map(productResourceAssembler::toResource)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.POST)
    public HttpEntity<?> createProduct(@RequestBody ProductResource productResource) {

        Optional<Product> optional = productService.createProduct(productResource);

        return optional.filter(Objects::nonNull)
                .map(productResourceAssembler::toResource)
                .map( resource -> ResponseEntity
                        .created(URI.create(resource.getId().getHref()))
                        .body(resource))
                .orElse(ResponseEntity.badRequest().build());
    }
}
