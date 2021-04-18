package com.chang.soloproject.solo_project.api.user;

import com.chang.soloproject.solo_project.api.user.dto.UserRes;
import com.chang.soloproject.solo_project.api.user.dto.UserSaveReq;
import com.chang.soloproject.solo_project.api.user.dto.UserSearchReq;
import com.chang.soloproject.solo_project.domain.user.User;
import com.chang.soloproject.solo_project.domain.user.UserService;
import com.chang.soloproject.solo_project.response.Result;
import com.chang.soloproject.solo_project.utill.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity findUsers(@ModelAttribute UserSearchReq userSearchReq){
        List<UserRes> users = userService.findUsers(userSearchReq);

        return ResponseEntity.ok(Result.success(users));
    }

    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserSaveReq userSaveReq) throws URISyntaxException {
        Long id = userService.saveUser(userSaveReq);

        return ResponseEntity.created(HttpUtil.getCurrentUri(id))
                .body(Result.success());
    }
}
