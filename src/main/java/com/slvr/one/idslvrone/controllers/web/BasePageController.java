package com.slvr.one.idslvrone.controllers.web;

import com.slvr.one.idslvrone.domain.User;
import com.slvr.one.idslvrone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BasePageController {
    @Autowired
    UserService userService;

    protected User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findUser(authentication.getName());
    }
}
