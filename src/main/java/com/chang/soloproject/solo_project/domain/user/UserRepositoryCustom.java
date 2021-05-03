package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.user.dto.UserRes;
import com.chang.soloproject.solo_project.api.user.dto.UserSearchReq;
import com.google.common.io.Files;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {

    /**
     * 사용자 목록 조회
     */
    List<User> findUsers(UserSearchReq userSearchReq);

    /**
     * 로그인 아이디로 사용자 조회
     */
    Optional<User> findByLoginId(String loginId);

    /**
     * 중복 사용자 조회 (로그인 아이디)
     */
    boolean existsUserByLoginId(String loginId);

}
