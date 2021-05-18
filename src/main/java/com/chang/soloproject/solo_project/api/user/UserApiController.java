package com.chang.soloproject.solo_project.api.user;

import com.chang.soloproject.solo_project.api.user.dto.UserRes;
import com.chang.soloproject.solo_project.api.user.dto.UserSaveReq;
import com.chang.soloproject.solo_project.api.user.dto.UserSearchReq;
import com.chang.soloproject.solo_project.api.user.dto.UserUpdateReq;
import com.chang.soloproject.solo_project.domain.user.User;
import com.chang.soloproject.solo_project.domain.user.UserService;
import com.chang.soloproject.solo_project.response.Result;
import com.chang.soloproject.solo_project.utill.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    /**
     * [사용자] 목록 조회
     */
    @GetMapping
    public ResponseEntity findUsers(@ModelAttribute UserSearchReq userSearchReq){
        List<UserRes> users = userService.findUsers(userSearchReq);

        return ResponseEntity.ok(Result.success(users));
    }

    /**
     * [사용자] 단건 조회
     */
    @GetMapping("/{userId}")
    public ResponseEntity findUser(@PathVariable Long userId){
        UserRes user = userService.findUser(userId);

        return ResponseEntity.ok(Result.success(user));
    }

    /**
     * [사용자] 단건 등록
     */
    @PostMapping
    public ResponseEntity saveUser(@RequestBody UserSaveReq userSaveReq) throws URISyntaxException {
        Long id = userService.saveUser(userSaveReq);

        return ResponseEntity.created(HttpUtil.getCurrentUri(id))
                .body(Result.success());
    }

    /**
     * [사용자] 단건 수정
     */
    @PutMapping("/{userId}")
    public ResponseEntity updateUser(@PathVariable Long userId,
                                      @RequestBody UserUpdateReq userUpdateReq) {
        Long updatedId = userService.updateUser(userId, userUpdateReq);

        return ResponseEntity.ok(Result.success(new UserRes(updatedId)));
    }

    /**
     * [사용자] 단건 삭제
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {

        log.debug("deleteUser: {}" + userId);
        userService.deleteUser(userId);

        return ResponseEntity.ok(Result.success());
    }
}
