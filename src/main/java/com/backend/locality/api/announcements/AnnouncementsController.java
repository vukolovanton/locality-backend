package com.backend.locality.api.announcements;

import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsRequest;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsResponse;
import com.backend.locality.api.announcements.interfaces.PostAnnouncementRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/announcements")
public class AnnouncementsController {
    private final AnnouncementsService announcementsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<IndexAnnouncementsResponse> getAllAnnouncements(@ModelAttribute IndexAnnouncementsRequest request) {
        return announcementsService.findAll(request);
    }

    @RequestMapping(method = RequestMethod.POST)
    public AnnouncementsModel saveAnnouncement(@RequestBody PostAnnouncementRequest request) {
        return announcementsService.saveAnnouncement(request);
    }

    @RequestMapping(value = "/{announcementId}", method = RequestMethod.GET)
    public AnnouncementsModel getAnnouncementById(@PathVariable Long announcementId) {
        return announcementsService.findAnnouncementById(announcementId);
    }
}
