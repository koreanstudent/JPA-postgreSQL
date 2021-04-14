package com.chang.soloproject.solo_project.api;


import com.chang.soloproject.solo_project.domain.user.User;
import com.chang.soloproject.solo_project.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    private Long id;
    private String loginId;
    private String password;
    private String name;
    private UserRole role;
    private String permissions;

    public Account(User entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.permissions = entity.getPermissions();

    }
}
