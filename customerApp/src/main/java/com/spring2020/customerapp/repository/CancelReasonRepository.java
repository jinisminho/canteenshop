package com.spring2020.customerapp.repository;

import com.spring2020.customerapp.domain.entity.CancelReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CancelReasonRepository extends JpaRepository<CancelReason, Long> {

    Optional<CancelReason> findByOrderId(long orderId);
}
