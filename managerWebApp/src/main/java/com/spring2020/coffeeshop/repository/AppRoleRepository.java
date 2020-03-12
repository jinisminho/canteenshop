package com.spring2020.coffeeshop.repository;

import com.spring2020.coffeeshop.domain.entity.AppRole;
import com.spring2020.coffeeshop.domain.enums.RoleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

    Optional<AppRole> findByName(RoleNameEnum name);
}
