package com.spring2020.coffeeshop.repository;

import com.spring2020.coffeeshop.domain.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {


    @Query(value =
            "SELECT * FROM customer_order WHERE create_at between :startDate and :endDate + interval 1 day",
            countQuery = "SELECT count(*) FROM customer_order",
            nativeQuery = true)
    Page<CustomerOrder> findOrderInPeriod(@Param("startDate") String startDate,
                                          @Param("endDate") String endDate,
                                          Pageable pageable);
}
