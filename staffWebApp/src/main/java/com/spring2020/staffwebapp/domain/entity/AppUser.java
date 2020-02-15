package com.spring2020.staffwebapp.domain.entity;

import com.spring2020.staffwebapp.domain.enums.GenderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "App_User")
public class AppUser extends Audit
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 100)
    private String phone;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false, length = 20)
    private String userType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_role_id")
    private AppRole appRole;

}
