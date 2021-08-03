package com.backend.locality.api.announcements;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/announcements")
public class AnnouncementsController {
    private final AnnouncementsService announcementsService;

    public List<AnnouncementsModel> getAllAnnouncements() {
        return announcementsService.findAll();
    }

    public AnnouncementsModel saveAnnouncement(AnnouncementsModel announcement) {
        return announcementsService.saveAnnouncement(announcement);
    }

    public AnnouncementsModel getAnnouncementById(int announcementId) {
        return announcementsService.findAnnouncementById(announcementId);
    }
}
