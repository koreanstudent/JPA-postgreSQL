package com.chang.soloproject.solo_project.api.login;

import com.chang.soloproject.solo_project.domain.user.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountUser {

    @JsonProperty("userId")
    private Long id;
    private String loginId;
    private String password;
    private String name;
    private UserRole role;
    private String permissions;

    public AccountUser(Account entity) {
        this.id = entity.getId();
        this.loginId = entity.getLoginId();
        this.password = entity.getPassword();
        this.name = entity.getName();
        this.permissions = entity.getPermissions();
    }
}