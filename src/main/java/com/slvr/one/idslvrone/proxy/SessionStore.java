package com.slvr.one.idslvrone.proxy;

import com.slvr.one.idslvrone.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionStore {
    User crtUser;

    public User getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(User crtUser) {
        this.crtUser = crtUser;
    }
}
