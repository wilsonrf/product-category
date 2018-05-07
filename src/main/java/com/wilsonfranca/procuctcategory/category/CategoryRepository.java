package com.wilsonfranca.procuctcategory.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * Created by wilson on 05/05/18.
 */
public interface CategoryRepository extends CrudRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {

    Optional<Category> findById(Long id);

    Page<Category> findAllByParentId(Long id, Pageable pageable);
}
