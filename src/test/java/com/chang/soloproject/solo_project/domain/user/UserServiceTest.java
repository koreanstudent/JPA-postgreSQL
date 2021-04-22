package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.user.dto.UserRes;
import com.chang.soloproject.solo_project.api.user.dto.UserSaveReq;
import com.chang.soloproject.solo_project.api.user.dto.UserSearchReq;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;



    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("유저아이디 체크 서비스")
    public void findUsers() {

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
        List<UserRes> resList = new ArrayList<>();

        given(userService.findUsers(any())).willReturn(resList);
    }
}