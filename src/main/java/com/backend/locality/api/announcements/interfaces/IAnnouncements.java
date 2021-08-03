package com.backend.locality.api.announcements.interfaces;

import com.backend.locality.api.announcements.AnnouncementsModel;

import java.util.List;

public interface IAnnouncements {
    List<AnnouncementsModel> findAll();
    AnnouncementsModel findAnnouncementById(int announcementId);
    AnnouncementsModel saveAnnouncement(AnnouncementsModel announcement);
}
