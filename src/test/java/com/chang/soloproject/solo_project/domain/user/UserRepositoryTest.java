package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.user.dto.UserSaveReq;
import com.chang.soloproject.solo_project.api.user.dto.UserSearchReq;
import com.chang.soloproject.solo_project.domain.common.TestConfig;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestConfig.class)
@Disabled
@ActiveProfiles("testDev")
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

//    @BeforeEach
//    public void set() {
//        userRepository.deleteAll();
//    }


    @Test
    @DisplayName("유저 저장(UserRepositoryTest)")

    void save() {

        //given
        String loginId = "hn123123";
        String password = "123";
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        UserSaveReq user = UserSaveReq.builder()
                .loginId(loginId)
                .name("이창현")
                .password(encodedPassword)
                .permissions("write")
                .role(UserRole.ADMIN)
                .build();

        String id;
        //when
        id = userRepository.save(user.toEntity()).getLoginId();

        //then
        assertEquals(id,loginId);
    }

    @Test
    @DisplayName("유저 목록 조회")
    public void findUsers() {
        String loginId = "hn123123";

        UserSearchReq userSearchReq = new UserSearchReq();

        userSearchReq.setLoginId(loginId);

        List<User> users = userRepository.findUsers(userSearchReq);


        User user = users.get(0);

        assertEquals(user.getLoginId(),loginId);

    }




}