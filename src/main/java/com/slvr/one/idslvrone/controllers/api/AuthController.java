package com.slvr.one.idslvrone.controllers.api;

import com.slvr.one.idslvrone.domain.User;
import com.slvr.one.idslvrone.domain.controllers.LoginRequest;
import com.slvr.one.idslvrone.proxy.SessionStore;
import com.slvr.one.idslvrone.services.AuthManager;
import com.slvr.one.idslvrone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthManager authManager;

    @Autowired
    SessionStore sessionStore;

    @Autowired
    UserService usersService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public LoginStatus login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword());
        User details = new User(loginRequest.getUsername());
        token.setDetails(details);

        try {
            Authentication authentication = authManager.authenticate(token);
            sessionStore.setCrtUser(usersService.findUser(authentication.getName()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new LoginStatus(authentication.isAuthenticated(), authentication.getName());
        } catch (AuthenticationException e) {
            return new LoginStatus(false, null);
        }
    }

    @GetMapping
    @ResponseBody
    public LoginStatus getStatus() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null && !auth.getName().equals("anonymous") && auth.isAuthenticated()) {
            return new LoginStatus(true, auth.getName());
        } else {
            return new LoginStatus(false, null);
        }
    }

    public class LoginStatus {
        private final boolean loggedIn;
        private final String username;

        public LoginStatus(boolean loggedIn, String username) {
            this.loggedIn = loggedIn;
            this.username = username;
        }

        public boolean isLoggedIn() {
            return loggedIn;
        }

        public String getUsername() {
            return username;
        }
    }
}
