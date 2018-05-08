package com.wilsonfranca.procuctcategory.product;

import com.wilsonfranca.procuctcategory.category.Category;
import com.wilsonfranca.procuctcategory.category.CategoryNotFoundException;
import com.wilsonfranca.procuctcategory.category.CategoryService;
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
public class ProductService {

    private ProductRepository productRepository;

    private CategoryService categoryService;

    @Autowired
    public ProductService(final ProductRepository productRepository, final CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> createProduct(ProductResource productResource) {
        Category category = categoryService.getCategoryById(productResource.getCategory())
                .filter(Objects::nonNull)
                .orElseThrow(CategoryNotFoundException::new);
        Product product = new Product();
        product.setCategory(category);
        product.setTitle(productResource.getTitle());
        product.setDescription(productResource.getDescription());
        Product persisted = productRepository.save(product);
        return Optional.ofNullable(persisted);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
