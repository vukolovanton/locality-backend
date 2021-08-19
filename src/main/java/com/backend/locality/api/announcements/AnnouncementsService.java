package com.backend.locality.api.announcements;

import com.backend.locality.api.AbstractPatchRequest;
import com.backend.locality.api.announcements.interfaces.IAnnouncements;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsRequest;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsResponse;
import com.backend.locality.api.announcements.interfaces.PostAnnouncementRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnnouncementsService implements IAnnouncements {
    private final AnnouncementsRepository announcementsRepository;

    @Override
    public List<IndexAnnouncementsResponse> findAll(IndexAnnouncementsRequest request) {
        return announcementsRepository.findAll(request);
    }

    @Override
    public AnnouncementsModel findAnnouncementById(Long announcementId) {
        return announcementsRepository.findAnnouncementById(announcementId);
    }

    @Override
    public AnnouncementsModel saveAnnouncement(PostAnnouncementRequest request) {
        return announcementsRepository.saveAnnouncement(request);
    }

    @Override
    public AnnouncementsModel patchAnnouncement(AbstractPatchRequest request) {
        return announcementsRepository.patchAnnouncement(request);
    }
}
