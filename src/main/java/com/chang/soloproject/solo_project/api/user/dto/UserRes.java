package com.chang.soloproject.solo_project.api.user.dto;

import com.chang.soloproject.solo_project.domain.user.User;
import com.chang.soloproject.solo_project.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRes {

    @JsonProperty("userId")
    private Long id;
    private String loginId;
    private String name;

    private UserRole role;
    private String roleTitle;
    private String permissions;

    public UserRes(Long id) {
        this.id = id;
    }

    public UserRes(User entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.name = entity.getName();

        this.role = entity.getRole();
        this.roleTitle = role.getTitle();
        this.permissions = entity.getPermissions();

    }
}
