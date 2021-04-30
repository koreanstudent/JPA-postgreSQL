package com.chang.soloproject.solo_project.api.login;

import com.chang.soloproject.solo_project.core.exception.BusinessException;
import com.chang.soloproject.solo_project.core.exception.ErrorCode;
import com.chang.soloproject.solo_project.response.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginApiController {

    // 실제 Authentication을 만들고 인증을 처리하는 인터페이스는 AuthenticationManager
    // AuthenticationManager는 authenticate라는 메서드만을 가지며
    // 여기서 authentication은 유저가 입력한 username, password 등의 인증정보를 담고 있다.
    // 유효한지 확인 후 UserDetailsService가 return 한 객체를 Principal로 담고 있는 Authentication 객체를 return 해준다.
    private final AuthenticationManager authenticationManager;

    /*
    *  사용자 로그인
    */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AccountGetReq accountGetReq, HttpSession httpSession) {
        String loginId = accountGetReq.getLoginId();
        String password = accountGetReq.getPassword();

        Authentication authentication;
        try {
            // Authentication을 implements한 AbstractAuthenticationToken의 하위 클래스로 username이 Principal의 역할을 하고, password가 Credential의 역할을 합니다
            // 첫번째 생성자는 인증 전의 객체를 생성하고, 두번째 생성자는 인증이 완료된 객체를 생성해줍니다.
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginId, password);
            authentication = authenticationManager.authenticate(token);
            // SecurityContextHolder는 보안 주체의 세부 정보를 포함하여 응용프래그램의 현재 보안 컨텍스트에 대한 세부 정보가 저장된다
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("### authentication: {}", authentication);
        } catch (Exception e){

            throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        }

        AccountAdapter accountAdapter = (AccountAdapter) authentication.getPrincipal();
        AccountUser currentUser = accountAdapter.getAccount();
        log.debug("### currentUser: {}", currentUser);

        int sessionTimeout = accountGetReq.getSessionTimeout();
        httpSession.setMaxInactiveInterval(sessionTimeout);
        log.debug("### sessionTimeout: {}s", sessionTimeout);

        return ResponseEntity.ok(Result.success(currentUser));
    }

}
