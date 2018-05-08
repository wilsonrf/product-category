package com.wilsonfranca.procuctcategory.product;

import com.wilsonfranca.procuctcategory.category.Category;
import com.wilsonfranca.procuctcategory.category.CategoryNotFoundException;
import com.wilsonfranca.procuctcategory.category.CategoryService;
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
public class ProductServiceTest {

    ProductService productService;

    ProductRepository productRepository;

    CategoryService categoryService;

    @Before
    public void setup(){
        productRepository = mock(ProductRepository.class);
        categoryService = mock(CategoryService.class);
        productService = new ProductService(productRepository, categoryService);
    }

    @Test(expected = CategoryNotFoundException.class)
    public void test_try_to_create_a_product_on_a_non_existing_category_and_got_category_not_found_exception() {

        when(categoryService.getCategoryById(1L)).thenReturn(Optional.empty());

        ProductResource productResource = new ProductResource();
        productResource.setDescription("description");
        productResource.setTitle("title");
        productResource.setCategory(1L);

        productService.createProduct(productResource);

        verify(categoryService, times(1)).getCategoryById(1L);

    }

    @Test
    public void test_try_to_create_a_product_on_a_existing_category_and_save_on_database_ok() {

        Category category = new Category();
        category.setId(1L);
        category.setName("category");

        when(categoryService.getCategoryById(1L)).thenReturn(Optional.of(category));

        when(productRepository.save(any(Product.class))).thenReturn(new Product());

        ProductResource productResource = new ProductResource();
        productResource.setDescription("description");
        productResource.setTitle("title");
        productResource.setCategory(1L);

        productService.createProduct(productResource);
        verify(categoryService, times(1)).getCategoryById(1L);
        verify(productRepository, times(1)).save(any(Product.class));

    }
}
