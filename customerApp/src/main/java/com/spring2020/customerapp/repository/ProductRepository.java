package com.spring2020.customerapp.repository;

import com.spring2020.customerapp.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value =
            "SELECT * FROM product WHERE is_available = :isAvailable and name like %:name%",
            countQuery = "SELECT count(*) FROM product",
            nativeQuery = true)
    Page<Product> findByNameContainingAndAvailable(String name, boolean isAvailable, Pageable pageable);

    @Query(value =
            "SELECT * FROM product WHERE is_available = :isAvailable",
            countQuery = "SELECT count(*) FROM product",
            nativeQuery = true)
    Page<Product> findByAvailable(boolean isAvailable, Pageable pageable);

    @Query(value =
            "SELECT * FROM product WHERE is_available = :isAvailable and id = :id",
            nativeQuery = true)
    Product findByIdAndAvailable(Long id, boolean isAvailable);

}
