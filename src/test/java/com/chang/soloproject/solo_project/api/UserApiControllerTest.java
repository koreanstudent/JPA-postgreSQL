package com.chang.soloproject.solo_project.api;

import com.chang.soloproject.solo_project.domain.common.BaseTest;
import com.chang.soloproject.solo_project.domain.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserApiControllerTest extends BaseTest {

    @Test
    @DisplayName("유저 저장 - /api/user")
    public void saveUser() throws Exception {

        String loginId = "hn123";
        String password = "123";
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        UserSaveReq request = UserSaveReq.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .name("이창현")
                .permissions("write")
                .role(UserRole.ADMIN)
                .build();

        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.post("/api/user")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8"))
                .andDo(print())
                .andDo(document("create-user",
                        requestHeaders(
                        headerWithName(HttpHeaders.ACCEPT).description("accept header")
                        ),
                        requestFields(
                                fieldWithPath("loginId").description("Name of new event"),
                                fieldWithPath("name").description("Name of new event"),
                                fieldWithPath("password").description("Name of new event"),
                                fieldWithPath("permissions").description("Name of new event"),
                                fieldWithPath("role").description("Name of new event")
                        )

                        ));

    }

}