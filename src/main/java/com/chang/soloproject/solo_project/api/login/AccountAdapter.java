package com.chang.soloproject.solo_project.api.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountAdapter extends User {

    private Account account;

    public AccountAdapter(Account account) {
        super(account.getLoginId(), account.getPassword(), authorities(account.getPermissions(), account.getRole().name()));
        this.account = account;
    }

    public AccountUser getAccount() {
        return new AccountUser(account);
    }

    // 권한 부여
    private static Collection<? extends GrantedAuthority> authorities(String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            addAuthority(authorities, role);
        }
        return authorities;
    }

    private static Collection<? extends GrantedAuthority> authorities(List<String> permissions, String... roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            addAuthority(authorities, permission);
        }
        for (String role : roles) {
            addAuthority(authorities, role);
        }
        return authorities;
    }
    private static void addAuthority(List<GrantedAuthority> authorities, String role) {
        if (role != null && !role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        authorities.add(new SimpleGrantedAuthority(role));
    }


}
