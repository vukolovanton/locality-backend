package com.backend.locality.api.announcements.interfaces;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class IndexAnnouncementsRequest {
    private Long localityId;
    private Integer limit;
    private Integer page;
    private AnnouncementsStatus status;
    private Optional<Boolean> isPinned;
    private Long announcementId;
    private String searchText;
}
