package com.backend.locality.api.announcements.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class IndexAnnouncementsResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private boolean isPinned;
    private AnnouncementsStatus status;
    private LocalDateTime createdAt;
    private String username;
    private String email;
}
