package com.spring2020.staffwebapp.repositories;

import com.spring2020.staffwebapp.domain.entity.CustomerOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long>
{
    Page<CustomerOrder> findAllByStaffId(Long id, Pageable pageable);

    Page<CustomerOrder> findAllByCreateAtIsBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<CustomerOrder> findAllByStatusId(int id, Pageable pageable);
}
