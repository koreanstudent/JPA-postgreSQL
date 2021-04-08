package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.domain.common.EnumMapperType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum UserPermission implements EnumMapperType {

    READ("읽기"),
    WRITE("쓰기"),
    UPDATE("수정"),
    DELETE("삭제");

    private final String title;

    public static UserPermission of(String title) {
        return Arrays.stream(values())
                .filter(v -> v.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(title));
    }

    @Override
    public String getCode() {
        return name();
    }
}
