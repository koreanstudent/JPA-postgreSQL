package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.UserSaveReq;
import com.chang.soloproject.solo_project.domain.common.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Rollback(false)
@Slf4j
public class UserServiceTest  {


    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }
    @Test
    @DisplayName("정상적으로 이벤트를 생성하는 테스트")
    public void createUser() throws Exception {
        String loginId = "hn123";
        String password = "123";

        UserSaveReq user = UserSaveReq.builder()
                .loginId(loginId)
                .name("이창현")
                .password(password)
                .permissions("write")
                .role(UserRole.ADMIN)
                .build();
        System.out.println("@@@@@@@@@@@@@" +user);

        userService.saveUser(user);

//        given(userService.saveUser(any(UserSaveReq.class))).willReturn(1L);
    }



}