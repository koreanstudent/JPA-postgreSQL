package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.Account;
import com.chang.soloproject.solo_project.api.UserSaveReq;
import com.mysema.commons.lang.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    LoginService loginService;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("로그인 테스트")
    public void findByLoginId() {

        //given
        String loginId = "hn123123";
        String password = "123";

        UserSaveReq user = UserSaveReq.builder()
                .loginId(loginId)
                .name("이창현")
                .password(password)
                .permissions("write")
                .role(UserRole.ADMIN)
                .build();

        userRepository.save(user.toEntity());

     /*   // when
        UserDetails userDetails = loginService.loadUserByUsername("hn123");

        // then
        assertThat(userDetails.getPassword()).isEqualTo(password);*/



    }
}