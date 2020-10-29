package com.spring2020.customerapp.repository;

import com.spring2020.customerapp.domain.entity.Customer;
import com.spring2020.customerapp.domain.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @Query(value =
            "SELECT * FROM customer_order WHERE customer_id = :customerId\n" +
                    "ORDER BY create_at desc\n" +
                    "LIMIT 10",
            nativeQuery = true)
    List<CustomerOrder> findCustomerOrdersByCustomer(@Param("customerId") long customerId);
}
