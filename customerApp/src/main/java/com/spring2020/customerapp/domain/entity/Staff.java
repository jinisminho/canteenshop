package com.spring2020.customerapp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Staff")
public class Staff extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length = 500)
    private String address;

    @Column(nullable = false, length = 12)
    private String socialId;

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column
    private LocalDate terminateDate;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
}
