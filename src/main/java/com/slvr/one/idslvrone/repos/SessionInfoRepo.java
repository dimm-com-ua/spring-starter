package com.slvr.one.idslvrone.repos;

import com.slvr.one.idslvrone.domain.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionInfoRepo extends JpaRepository<SessionInfo, Long> {
    SessionInfo findBySessionId(String sessionId);
}
