package com.spring2020.staffwebapp.repositories;

import com.spring2020.staffwebapp.domain.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>
{
    Optional<Staff> findStaffByAppUserId(Long id);
}
