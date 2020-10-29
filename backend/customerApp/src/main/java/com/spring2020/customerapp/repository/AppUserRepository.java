package com.spring2020.customerapp.repository;

import com.spring2020.customerapp.domain.entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    @Query(value =
            "SELECT * FROM app_user WHERE first_name LIKE CONCAT(:name,'%') AND user_type = :userType\n" +
                    "UNION \n" +
                    "SELECT * FROM app_user WHERE last_name LIKE CONCAT(:name,'%') AND user_type = :userType",
            countQuery = "SELECT count(*) FROM app_user",
            nativeQuery = true)
    Page<AppUser> findByNameAndUserType(@Param("name") String name,
                                        @Param("userType") String userType,
                                        Pageable pageable);

    Optional<AppUser> findByUsername(String username);


}
