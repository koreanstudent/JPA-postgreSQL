package com.chang.soloproject.solo_project.api;

import com.chang.soloproject.solo_project.api.user.dto.UserSaveReq;
import com.chang.soloproject.solo_project.api.user.dto.UserUpdateReq;
import com.chang.soloproject.solo_project.domain.common.BaseTest;
import com.chang.soloproject.solo_project.domain.user.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.attributes;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser
public class UserApiControllerTest extends BaseTest {

    @Test
    @DisplayName("유저 목록조회 - /api/user")
    public void findUsers() throws Exception {



        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8"))
                .andDo(print())
                .andDo(document("find-users",

                        responseFields(
                                fieldWithPath("data[0].userId").type(JsonFieldType.NUMBER).description("유저 아이디"),
                                fieldWithPath("data[0].loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
                                fieldWithPath("data[0].password").type(JsonFieldType.STRING).description("로그인 비밀번호"),
                                fieldWithPath("data[0].name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("data[0].role").type(JsonFieldType.STRING).description("직위"),
                                fieldWithPath("data[0].roleTitle").type(JsonFieldType.STRING).description("직위 한글"),
                                fieldWithPath("data[0].permissions").type(JsonFieldType.STRING).description("권한"),
                                fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                                fieldWithPath("isSuccess").type(JsonFieldType.BOOLEAN).description("성공여부")


                                )


                ));

    }

    @Test
    @DisplayName("유저 단건조회 - /api/user/{userid}")
    public void findUser() throws Exception {

        String userId = "1";


        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.get("/api/user/{userId}" ,userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8"))
                .andDo(print())
                .andDo(document("find-user",

                    pathParameters(
                            parameterWithName("userId").description("사용자 아이디")
                    ),

                    responseFields(
                            fieldWithPath("data.userId").type(JsonFieldType.NUMBER).description("유저 아이디"),
                            fieldWithPath("data.loginId").type(JsonFieldType.STRING).description("로그인 아이디"),
                            fieldWithPath("data.password").type(JsonFieldType.STRING).description("로그인 비밀번호"),
                            fieldWithPath("data.name").type(JsonFieldType.STRING).description("이름"),
                            fieldWithPath("data.role").type(JsonFieldType.STRING).description("직위"),
                            fieldWithPath("data.roleTitle").type(JsonFieldType.STRING).description("직위 한글"),
                            fieldWithPath("data.permissions").type(JsonFieldType.STRING).description("권한"),
                            fieldWithPath("code").type(JsonFieldType.STRING).description("코드"),
                            fieldWithPath("message").type(JsonFieldType.STRING).description("메세지"),
                            fieldWithPath("isSuccess").type(JsonFieldType.BOOLEAN).description("성공여부")
                    )


                ));

    }

    @Test
    @DisplayName("유저 저장 - /api/user")
    public void saveUser() throws Exception {

        String loginId = "hn123123";
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
                                fieldWithPath("loginId").description("로그인 아이디"),
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("password").description("비밀번호"),
                                fieldWithPath("permissions").description("권한"),
                                fieldWithPath("role").description("직위")
                        )

                        ));

    }

    @Test
    @DisplayName("유저 수정- /api/user/{userId}")
    public void updateUser() throws Exception {
        String userId = "1";

        String password = "123";
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        UserUpdateReq request = UserUpdateReq.builder()
                .password(encodedPassword)
                .name("이창현")
                .permissions("write")
                .role(UserRole.ADMIN)
                .build();

        ResultActions result = mockMvc.perform(
                RestDocumentationRequestBuilders.put("/api/user/{userId}", userId)
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        );

        result
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8"))
                .andDo(print())
                .andDo(document("update-user",

                        pathParameters(
                                parameterWithName("userId").description("사용자 아이디")
                        ),
                        requestFields(
                                attributes(key("title").value("Fields for user creation")),
                                fieldWithPath("loginId").type(JsonFieldType.STRING).attributes(key("format").value("A|B|O|AB")).description("로그인 아이디").optional(),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름").optional(),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호").optional(),
                                fieldWithPath("permissions").type(JsonFieldType.STRING).description("권한").optional(),
                                fieldWithPath("role").type(JsonFieldType.STRING).attributes(key("format").value("admin,user")).description("직위").optional()
                        )


                ));

    }

}