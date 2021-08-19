package com.backend.locality.api.announcements.interfaces;

import com.backend.locality.api.AbstractPatchRequest;
import com.backend.locality.api.announcements.AnnouncementsModel;

import java.util.List;

public interface IAnnouncements {
    List<IndexAnnouncementsResponse> findAll(IndexAnnouncementsRequest request);
    AnnouncementsModel findAnnouncementById(Long announcementId);
    AnnouncementsModel saveAnnouncement(PostAnnouncementRequest request);
    AnnouncementsModel patchAnnouncement(AbstractPatchRequest request);
}
