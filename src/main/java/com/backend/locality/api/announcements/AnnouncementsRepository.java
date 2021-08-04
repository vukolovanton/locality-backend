package com.backend.locality.api.announcements;

import com.backend.locality.api.announcements.interfaces.IAnnouncements;
import com.backend.locality.api.locality.LocalityModel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class AnnouncementsRepository implements IAnnouncements {
    private final EntityManager entityManager;

    @Override
    public List<AnnouncementsModel> findAll() {
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<AnnouncementsModel> findAllQuery = session.createQuery(
                "Select a from AnnouncementsModel a",
                AnnouncementsModel.class
        );

        return findAllQuery.getResultList();
    }

    @Override
    public AnnouncementsModel findAnnouncementById(Long announcementId) {
        Session session = entityManager.unwrap(Session.class);

        TypedQuery<AnnouncementsModel> findLocalityByIdQuery = session.createQuery(
                "Select a from AnnouncementsModel a where a.id = :announcementId",
                AnnouncementsModel.class
        );
        findLocalityByIdQuery.setParameter("announcementId", announcementId);

        return findLocalityByIdQuery.getSingleResult();
    }

    @Override
    public AnnouncementsModel saveAnnouncement(AnnouncementsModel announcement) {
        Session session = entityManager.unwrap(Session.class);
        session.save(announcement);

        // Or is it going to have id?
        return announcement;
    }
}
