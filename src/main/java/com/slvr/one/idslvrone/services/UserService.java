package com.slvr.one.idslvrone.services;

import com.slvr.one.idslvrone.domain.User;
import com.slvr.one.idslvrone.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User findUser(String userName) {
        return userRepo.findByUsername(userName);
    }
}
