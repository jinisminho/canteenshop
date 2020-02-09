package com.spring2020.staffwebapp.repository;

import com.spring2020.staffwebapp.domain.entity.CancelReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CancelReasonRepository extends JpaRepository<CancelReason, Long> {
    Optional<CancelReason> findByOrderId(Long id);
}
