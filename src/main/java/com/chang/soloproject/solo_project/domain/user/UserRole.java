package com.chang.soloproject.solo_project.domain.user;


import com.chang.soloproject.solo_project.domain.common.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserRole implements EnumMapperType {

    ADMIN("관리자"),
    HEAD("본사"),
    SALES("영업"),
    INSTALLATION("설치");

    private final String title;

    public static UserRole of(String title) {
        return Arrays.stream(values())
                .filter(v -> v.getTitle().equals(title))
                .findFirst().orElseThrow(() -> new UsernameNotFoundException(title));
    }

    @Override
    public String getCode() {
        return name();
    }
}
