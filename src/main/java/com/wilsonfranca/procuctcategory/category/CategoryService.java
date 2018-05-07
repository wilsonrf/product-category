package com.wilsonfranca.procuctcategory.category;

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
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryById(final Long id) {

        return categoryRepository.findById(id);
    }

    public Page<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Optional<Category> createCategory(CategoryResource categoryResource) {

        Category category = new Category();
        category.setName(categoryResource.getName());
        Category persisted = categoryRepository.save(category);
        return Optional.ofNullable(persisted);

    }

    public Page<Category> getCategoryChildren(Long id, Pageable pageable) {
        return categoryRepository.findAllByParentId(id, pageable);
    }

    public Optional<Category> createCategoryChildren(Long parentId, CategoryResource categoryResource) {

        Optional<Category> optional = categoryRepository.findById(parentId);
        Category parent = optional.filter(Objects::nonNull)
                .orElseThrow(RuntimeException::new);
        Category category = new Category();
        category.setName(categoryResource.getName());
        category.setParent(parent);
        Category persisted = categoryRepository.save(category);

        return Optional.ofNullable(persisted);
    }
}
