package com.backend.locality.api.announcements;

import com.backend.locality.api.AbstractPatchRequest;
import com.backend.locality.api.announcements.interfaces.*;
import com.backend.locality.api.users.UserModel;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

        // Add id
        if (request.getAnnouncementId() != null) {
            String condition = "and a.id = :announcementId";
            sb.append(condition);
        }

        // Add status
        if (request.getStatus() != null) {
            String condition = "and a.status = :status";
            sb.append(condition);
        }

        // Add isPinned
        if (Objects.nonNull(request.getIsPinned())) {
            String condition = "and a.isPinned = :isPinned";
            sb.append(condition);
        }


        // Add searchText
        if (request.getSearchText() != null) {
            String condition = "and a.title like :searchText";
            sb.append(condition);
        }

        sb.append(" order by a.createdAt DESC");

        // Create query
        TypedQuery<IndexAnnouncementsResponse> findAllQuery = session.createQuery(
                sb.toString(), IndexAnnouncementsResponse.class
        );

        findAllQuery.setParameter("localityId", request.getLocalityId());

        // Filter by id
        if (request.getAnnouncementId() != null) {
            findAllQuery.setParameter("announcementId", request.getAnnouncementId());
        }

        // Filer by status
        if (request.getStatus() != null) {
            findAllQuery.setParameter("status", request.getStatus());
        }

        // Filer by pinned
        if (Objects.nonNull(request.getIsPinned())) {
            findAllQuery.setParameter("isPinned", request.getIsPinned().get());
        }

        // Filter by searchText
        if (request.getSearchText() != null) {
            findAllQuery.setParameter("searchText", "%" + request.getSearchText() + "%");
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

    @Override
    // TODO: Refactor this
    @Transactional
    @Modifying
    public AnnouncementsModel patchAnnouncement(AbstractPatchRequest request) {
        Session session = entityManager.unwrap(Session.class);

        // Separate query
        String update = "UPDATE AnnouncementsModel ";
        String set = "set " + request.getKey() + " = :value";
        String where = " where id = :entityId";

        //Combine query
        Query updateQuery = session.createQuery(
                update + set + where
        );

        // handle ENUM
        if (request.getKey().equals("status")) {
            updateQuery.setParameter("value", AnnouncementsStatus.valueOf(request.getValue()));
        // handle BOOLEAN
        } else if (request.getKey().equals("isPinned")) {
            updateQuery.setParameter("value", Boolean.valueOf(request.getValue()));
        // handle STRING
        } else {
            updateQuery.setParameter("value", request.getValue());
        }

        // Set last required param
        updateQuery.setParameter("entityId", request.getEntityId());
        updateQuery.executeUpdate();

        // TODO: return real Issue model
        return new AnnouncementsModel();
    }

    private int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }

}
