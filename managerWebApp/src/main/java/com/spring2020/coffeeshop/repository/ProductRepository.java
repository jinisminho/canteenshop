package com.spring2020.coffeeshop.repository;

import com.spring2020.coffeeshop.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value =
            "SELECT p.* FROM category c\n" +
                    "JOIN product p \n" +
                    "ON  c.id = p.category_id and c.name LIKE :categoryName",
            countQuery =
                    "SELECT count(p.id) FROM category c\n" +
                            "JOIN product p \n" +
                            "ON  c.id = p.category_id and c.name LIKE :categoryName",
            nativeQuery = true)
    Page<Product> findByCategory(String categoryName, Pageable pageable);

}
