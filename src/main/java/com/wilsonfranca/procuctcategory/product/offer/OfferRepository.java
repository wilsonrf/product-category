package com.wilsonfranca.procuctcategory.product.offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by wilson on 06/05/18.
 */
@Repository
public interface OfferRepository extends CrudRepository<Offer, Long>, PagingAndSortingRepository<Offer, Long> {

    Optional<Offer> findById(Long id);

    Page<Offer> findAllByProductId(Long productId, Pageable pageable);

    Optional<Offer> findByIdAndProductId(Long id, Long productId);
}
