package com.backend.locality.api.announcements.interfaces;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchAnnouncementRequest {
    private Long issueId;
    private String key;
    private String value;
}
