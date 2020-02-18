package com.spring2020.customerapp.repository;

import com.spring2020.customerapp.domain.entity.Customer;
import com.spring2020.customerapp.domain.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @Query(value =
            "SELECT * FROM customer_order WHERE customer_id = :customerId",
            countQuery = "SELECT count(*) FROM customer_order",
            nativeQuery = true)
    Page<CustomerOrder> findCustomerOrdersByCustomer(@Param("customerId") int customerId, Pageable pageable);
}
