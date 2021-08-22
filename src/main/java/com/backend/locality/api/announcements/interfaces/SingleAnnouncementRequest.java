package com.backend.locality.api.announcements.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SingleAnnouncementRequest {
    private Long localityId;
    private Long announcementId;
}
