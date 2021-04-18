package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.user.dto.UserRes;
import com.chang.soloproject.solo_project.api.user.dto.UserSaveReq;
import com.chang.soloproject.solo_project.api.user.dto.UserSearchReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    /**
     * [사용자] 단건 등록
     */
    public Long saveUser(UserSaveReq userSaveReq) {
        // 패스워드 암호화
        String rawPassword = userSaveReq.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        userSaveReq.changePassword(encodedPassword);


        return userRepository.save(userSaveReq.toEntity()).getId();

    }

    public List<UserRes> findUsers(UserSearchReq userSearchReq){
        return userRepository.findUsers(userSearchReq).stream().map(UserRes::new).collect(Collectors.toList());

    }
}
