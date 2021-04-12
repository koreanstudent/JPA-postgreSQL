package com.chang.soloproject.solo_project.api;

import com.chang.soloproject.solo_project.domain.user.User;
import com.chang.soloproject.solo_project.domain.user.UserPermission;
import com.chang.soloproject.solo_project.domain.user.UserRole;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Data
@Builder
public class UserSaveReq {

    @NotBlank
    @Size(max = 16)
    private String loginId;

    @NotBlank
    @Size(max = 32)
    private String password;

    @NotBlank
    @Size(max = 16)
    private String name;

    private String permissions;

    @NotNull
    private UserRole role;

    public void changePassword(String password) {
        this.password = password;
    }

    public User toEntity() {
        System.out.println("toEntity" + loginId);
        return User.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .permissions(permissions)
                .role(role)
                .build();
    }

}