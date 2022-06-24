package com.slvr.one.idslvrone.repos;

import com.slvr.one.idslvrone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
