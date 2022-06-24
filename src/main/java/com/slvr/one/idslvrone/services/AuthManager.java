package com.slvr.one.idslvrone.services;

import com.slvr.one.idslvrone.domain.Role;
import com.slvr.one.idslvrone.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

public class AuthManager implements AuthenticationManager {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        User user = userService.findUser(username);
        if(user == null) {
            throw new BadCredentialsException("1000");
        }
        String passw = bCryptPasswordEncoder.encode(password);
        System.out.println(passw);
        if(!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }
        if(user.getDisabled()) {
            throw new BadCredentialsException("1001");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role: user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
    }
}
