package com.backend.locality.api.dashboard;

import com.backend.locality.api.announcements.AnnouncementsModel;
import com.backend.locality.api.announcements.interfaces.AnnouncementsStatus;
import com.backend.locality.api.dashboard.interfaces.IndexDashboardRequest;
import com.backend.locality.api.issues.IssueStatuses;
import com.backend.locality.api.issues.IssuesModel;
import com.backend.locality.api.users.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@AllArgsConstructor
@Slf4j
public class DashboardRepository {
    private final EntityManager entityManager;

    @Transactional
    public HashMap<String, Long> issuesStatistics(IndexDashboardRequest request) {
        Session session = entityManager.unwrap(Session.class);

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<IssuesModel> issuesRoot = criteriaQuery.from(IssuesModel.class);

        HashMap<String, Long> statusesTotal = new HashMap<>();
        IssueStatuses[] statuses = IssueStatuses.values();

        for (IssueStatuses s : statuses) {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(issuesRoot.get("status"), s));
            predicates.add(criteriaBuilder.equal(issuesRoot.get("localityId"), request.getLocalityId()));

            criteriaQuery.select(criteriaBuilder.count(issuesRoot)).where(predicates.toArray(new Predicate[] {}));

            Long res = session.createQuery(criteriaQuery).getSingleResult();
            statusesTotal.put(s.toString(), res);
        }

        return statusesTotal;
    }

    @Transactional
    public HashMap<String, Long> usersStatistic(IndexDashboardRequest request) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<UserModel> usersRoot = criteriaQuery.from(UserModel.class);
        criteriaQuery.select(criteriaBuilder.count(usersRoot)).where(criteriaBuilder.equal(usersRoot.get("localityId"), request.getLocalityId()));
        Long usersCount = session.createQuery(criteriaQuery).getSingleResult();

        HashMap<String, Long> result = new HashMap<>();
        result.put("TOTAL_USERS", usersCount);

        return result;
    }

    @Transactional
    public HashMap<String, Long> announcementsStatistic(IndexDashboardRequest request) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

        Root<AnnouncementsModel> announcementsRoot = criteriaQuery.from(AnnouncementsModel.class);

        HashMap<String, Long> announcementsTotal = new HashMap<>();
        AnnouncementsStatus[] statuses = AnnouncementsStatus.values();

        for (AnnouncementsStatus s : statuses) {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(announcementsRoot.get("status"), s));
            predicates.add(criteriaBuilder.equal(announcementsRoot.get("localityId"), request.getLocalityId()));

            criteriaQuery.select(criteriaBuilder.count(announcementsRoot)).where(predicates.toArray(new Predicate[] {}));

            Long res = session.createQuery(criteriaQuery).getSingleResult();
            announcementsTotal.put(s.toString(), res);
        }

        return announcementsTotal;
    }


}
