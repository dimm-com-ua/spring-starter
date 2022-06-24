package com.slvr.one.idslvrone.services;

import com.slvr.one.idslvrone.domain.SessionInfo;
import com.slvr.one.idslvrone.repos.SessionInfoRepo;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    private final SessionInfoRepo sessionInfoRepo;

    public SessionService(SessionInfoRepo sessionInfoRepo) {
        this.sessionInfoRepo = sessionInfoRepo;
    }

    public SessionInfo getSessionInfo(String sessionId) { return this.sessionInfoRepo.findBySessionId(sessionId);}

    public void save(SessionInfo sessionInfo) {
        this.sessionInfoRepo.save(sessionInfo);
    }
}
