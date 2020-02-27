package com.spring2020.staffwebapp.repositories;

import com.spring2020.staffwebapp.domain.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>
{
    Page<CustomerOrder> findAllByStaffId(Long id, Pageable pageable);

    @Query(value =
            "SELECT * FROM customer_order WHERE create_at between ?1 and ?2 +  interval 1 day",
            countQuery = "SELECT count(*) FROM customer_order",
            nativeQuery = true)
    Page<CustomerOrder> findAllByCreateAtBetween(String startDate, String endDate, Pageable pageable);

    @Query(value =
            "SELECT * FROM customer_order WHERE status_id = ?3 AND (create_at between ?1 and ?2 +  interval 1 day)",
            countQuery = "SELECT count(*) FROM customer_order",
            nativeQuery = true)
    Page<CustomerOrder> findAllByPeriodAndStatus(String startDate, String endDate, int status, Pageable pageable);

    Page<CustomerOrder> findAllByStatusId(int id, Pageable pageable);
}
