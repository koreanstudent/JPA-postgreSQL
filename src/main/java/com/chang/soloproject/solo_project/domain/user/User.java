package com.chang.soloproject.solo_project.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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

    @Column(name ="user_login_id", unique = true)
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

    public void update(String password, String name, String permissions, UserRole role) {
        if (password != null) this.password = password;
        if (name != null) this.name = name;
        if (permissions != null) this.permissions =permissions;
        if (role != null) this.role = role;
    }


}
