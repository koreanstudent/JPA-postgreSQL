package com.chang.soloproject.solo_project.config;

import com.chang.soloproject.solo_project.api.user.dto.UserSaveReq;
import com.chang.soloproject.solo_project.domain.user.UserRepository;
import com.chang.soloproject.solo_project.domain.user.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class AppConfig {

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();

        characterEncodingFilter.setEncoding("UTF-8");

        characterEncodingFilter.setForceEncoding(true);

        return characterEncodingFilter;

    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            UserRepository userRepository;
            @Override
            public void run(ApplicationArguments args) throws Exception {

                String encodedPassword = new BCryptPasswordEncoder().encode("123");

                if(!userRepository.existsUserByLoginId("hn123")){
                    UserSaveReq user = UserSaveReq.builder()
                            .loginId("hn123")
                            .name("이창현")
                            .password(encodedPassword)
                            .permissions("write")
                            .role(UserRole.ADMIN)
                            .build();
                    userRepository.save(user.toEntity());
                }
            }
        };
    }
}
