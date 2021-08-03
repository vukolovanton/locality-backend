package com.backend.locality.api.announcements;

import com.backend.locality.api.announcements.interfaces.IAnnouncements;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementsService implements IAnnouncements {
    private final AnnouncementsRepository announcementsRepository;

    @Override
    public List<AnnouncementsModel> findAll() {
        return announcementsRepository.findAll();
    }

    @Override
    public AnnouncementsModel findAnnouncementById(int announcementId) {
        return announcementsRepository.findAnnouncementById(announcementId);
    }

    @Override
    public AnnouncementsModel saveAnnouncement(AnnouncementsModel announcement) {
        return announcementsRepository.saveAnnouncement(announcement);
    }
}
