package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.UserSaveReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    /**
     * [사용자] 단건 등록
     */
    @Transactional
    public Long saveUser(UserSaveReq userSaveReq) {
        // 패스워드 암호화
        String rawPassword = userSaveReq.getPassword();
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        userSaveReq.changePassword(encodedPassword);

        System.out.println("@@@@@@@@@@@@@" +userSaveReq);
        return userRepository.save(userSaveReq.toEntity()).getId();

    }
}
