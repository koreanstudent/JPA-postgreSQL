package com.chang.soloproject.solo_project.api.user.dto;


import com.chang.soloproject.solo_project.domain.user.UserRole;
import lombok.Data;

@Data
public class UserSearchReq {

    private Long userId;
    private String loginId;
    private String name;

//    @NumberOnly(fieldType = OPTIONAL, min = 10, max = 11)
    private String phoneNumber;

    private UserRole role;
    private Boolean enabled;

}
