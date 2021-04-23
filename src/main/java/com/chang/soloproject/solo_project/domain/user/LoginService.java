package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.login.Account;
import com.chang.soloproject.solo_project.api.login.AccountAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Account account = findUserByLoginId(loginId);
        System.out.println("LoginService account {}" + account);
        log.debug("LoginService account {}", account);
        return new AccountAdapter(account);
    }

    public Account findUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(Account::new)
                .orElseThrow(() -> new UsernameNotFoundException(loginId));
    }
}
