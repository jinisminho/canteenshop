package com.spring2020.staffwebapp.repository;

import com.spring2020.staffwebapp.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{

    @Query(value = "SELECT * FROM coffee_shop.product p\n" +
            "where 0=0\n" +
            "and (p.name like ?1 or ?1 like '')\n" +
            "and (p.category_id = ?2 or ?2 like '')\n" +
            "and (p.is_available = ?3 or false)"
            , countQuery = "SELECT count(*) FROM coffee_shop.product p\n" +
            "where 0=0\n" +
            "and (p.name like ?1 or ?1 like '')\n" +
            "and (p.category_id = ?2 or ?2 like '')\n" +
            "and (p.is_available = ?3 or false)"
            , nativeQuery = true)
    Page<Product> findProducts(String name, String categoryId, boolean isAvailable, Pageable pageable);
}
