package com.chang.soloproject.solo_project.domain.user;

import java.util.Optional;

public interface UserRepositoryCustom {

    /**
     * 로그인 아이디로 사용자 조회
     */
    Optional<User> findByLoginId(String loginId);

    /**
     * 중복 사용자 조회 (로그인 아이디)
     */
    boolean existsUserByLoginId(String loginId);
}
