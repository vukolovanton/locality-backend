package com.backend.locality.api.announcements;

import com.backend.locality.api.announcements.interfaces.AnnouncementsStatus;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsRequest;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsResponse;
import com.backend.locality.api.announcements.interfaces.PostAnnouncementRequest;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("classpath:application.properties")
@SpringBootTest
@AutoConfigureDataJpa
class AnnouncementsRepositoryTest {

    @Autowired
    private AnnouncementsRepository announcementsRepository;

    @Before
    public void setUp() {
        PostAnnouncementRequest request = new PostAnnouncementRequest(1L, 2L, "AnnouncementCreatedForTest", "Description", "http://google.com", AnnouncementsStatus.ACTIVE, true);
        AnnouncementsModel t = announcementsRepository.saveAnnouncement(request);
    }

    @Test
    void saveAnnouncement() {
        PostAnnouncementRequest request = new PostAnnouncementRequest(1L, 2L, "Title", "Description", "http://google.com", AnnouncementsStatus.ACTIVE, true);
        AnnouncementsModel t = announcementsRepository.saveAnnouncement(request);

        Assertions.assertEquals(request.getTitle(), t.getTitle());
    }

    @Test
    void findAll() {
        IndexAnnouncementsRequest request = new IndexAnnouncementsRequest();
        request.setLocalityId(2L);
        request.setSearchText("AnnouncementCreatedForTest");

        List<IndexAnnouncementsResponse> found = announcementsRepository.findAll(request);
        Assertions.assertNotNull(found);
    }
}