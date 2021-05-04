package com.chang.soloproject.solo_project.api.user.dto;

import com.chang.soloproject.solo_project.domain.user.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserUpdateReq {

    private String password;
    private String name;
    private String permissions;
    private UserRole role;

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    @Builder
    public UserUpdateReq(String password, String name, String permissions, UserRole role){
        this.password =password;
        this.name = name;
        this.permissions = permissions;
        this.role = role;
    }
}