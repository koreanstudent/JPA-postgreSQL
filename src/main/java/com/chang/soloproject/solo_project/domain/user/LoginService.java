package com.chang.soloproject.solo_project.domain.user;

import com.chang.soloproject.solo_project.api.login.Account;
import com.chang.soloproject.solo_project.api.login.AccountAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        return new AccountAdapter(findUserByLoginId(loginId));
    }

    public Account findUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId)
                .map(Account::new)
                .orElseThrow(() -> new UsernameNotFoundException(loginId));
    }
}
