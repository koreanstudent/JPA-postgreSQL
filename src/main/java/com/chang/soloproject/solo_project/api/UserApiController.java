package com.chang.soloproject.solo_project.api;

import com.chang.soloproject.solo_project.domain.user.UserService;
import com.chang.soloproject.solo_project.response.Result;
import com.chang.soloproject.solo_project.utill.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserSaveReq userSaveReq) throws URISyntaxException {
        Long id = userService.saveUser(userSaveReq);

        return ResponseEntity.created(HttpUtil.getCurrentUri(id))
                .body(Result.success());
    }
}
