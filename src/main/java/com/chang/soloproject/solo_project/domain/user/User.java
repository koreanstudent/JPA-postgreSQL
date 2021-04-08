package com.chang.soloproject.solo_project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name ="user_login_id")
    private String loginId;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String name;



    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole role; // 사용자구분

    @Column(name = "user_permissions")
    private String permissions;  // 사용자 권한 READ, WRITE, UPDATE, DELETE


}
