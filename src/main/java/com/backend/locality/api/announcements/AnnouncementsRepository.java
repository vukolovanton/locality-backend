package com.backend.locality.api.announcements;

import com.backend.locality.api.announcements.interfaces.IAnnouncements;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsRequest;
import com.backend.locality.api.announcements.interfaces.IndexAnnouncementsResponse;
import com.backend.locality.api.announcements.interfaces.PostAnnouncementRequest;
import com.backend.locality.api.locality.LocalityModel;
import com.backend.locality.api.users.UserModel;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AnnouncementsRepository implements IAnnouncements {
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<IndexAnnouncementsResponse> findAll(IndexAnnouncementsRequest request) {
        Session session = entityManager.unwrap(Session.class);

        String select = "Select new com.backend.locality.api.announcements.interfaces.IndexAnnouncementsResponse";
        String items = "(a.id, a.title, a.description, a.imageUrl, a.isPinned, a.status, a.createdAt, a.user.username, a.user.email)";
        String from = "from AnnouncementsModel a";
        String where = "where a.localityId = :localityId";

        // Combine strings
        StringBuilder sb = new StringBuilder();
        List<String> conditions = Arrays.asList(select, items, from, where);
        for (String s : conditions) {
            sb.append(s);
            sb.append(" ");
        }
        // Add status
        if (request.getStatus() != null) {
            String condition = "and a.status = :status";
            sb.append(condition);
        }
        // Create query
        TypedQuery<IndexAnnouncementsResponse> findAllQuery = session.createQuery(
                sb.toString(), IndexAnnouncementsResponse.class
        );
        findAllQuery.setParameter("localityId", request.getLocalityId());
        // Filer by status
        if (request.getStatus() != null) {
            findAllQuery.setParameter("status", request.getStatus());
        }
        // If request has limit, use it
        if (request.getLimit() != null) {
            findAllQuery.setMaxResults(request.getLimit());
            if (request.getPage() != null) {
                findAllQuery.setFirstResult(calculateOffset(request.getPage(), request.getLimit()));
                findAllQuery.setMaxResults(request.getLimit());
            }

        }
        return findAllQuery.getResultList();
    }

    @Override
    @Transactional
    public AnnouncementsModel findAnnouncementById(Long announcementId) {
        Session session = entityManager.unwrap(Session.class);

        TypedQuery<AnnouncementsModel> findAnnouncementByIdQuery = session.createQuery(
                "Select a from AnnouncementsModel a where a.id = :announcementId",
                AnnouncementsModel.class
        );
        findAnnouncementByIdQuery.setParameter("announcementId", announcementId);

        return findAnnouncementByIdQuery.getSingleResult();
    }

    @Override
    @Transactional
    public AnnouncementsModel saveAnnouncement(PostAnnouncementRequest request) {
        Session session = entityManager.unwrap(Session.class);
        Optional<UserModel> user = userRepository.findById(request.getUserId());

        AnnouncementsModel announcement = new AnnouncementsModel(
                request.getTitle(),
                request.getDescription(),
                request.getImageUrl(),
                request.isPinned(),
                request.getStatus(),
                request.getLocalityId(),
                null
        );

        if (user.isPresent()) {
            announcement.setUser(user.get());
            session.persist(announcement);
            session.flush();
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return announcement;
    }

    private static int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }
}
