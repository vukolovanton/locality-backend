package com.backend.locality.api.announcements;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/announcements")
public class AnnouncementsController {
    private final AnnouncementsService announcementsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<AnnouncementsModel> getAllAnnouncements() {
        return announcementsService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public AnnouncementsModel saveAnnouncement(@RequestBody AnnouncementsModel announcement) {
        return announcementsService.saveAnnouncement(announcement);
    }

    @RequestMapping(value = "/:id", method = RequestMethod.GET)
    public AnnouncementsModel getAnnouncementById(@ModelAttribute Long announcementId) {
        return announcementsService.findAnnouncementById(announcementId);
    }
}
