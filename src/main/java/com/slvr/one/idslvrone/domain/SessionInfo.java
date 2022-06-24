package com.slvr.one.idslvrone.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.slvr.one.idslvrone.domain.views.SessionInfoViews;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_session_info")
@Data
public class SessionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(SessionInfoViews.Id.class)
    private String sessionId;

    public SessionInfo(String sessionId) {
        this.sessionId = sessionId;
    }
}
