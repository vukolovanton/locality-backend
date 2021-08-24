package com.backend.locality.api.issues;

import com.backend.locality.api.issues.interfaces.IIssues;
import com.backend.locality.api.users.UserModel;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class IssuesRepository implements IIssues {
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Transactional
    public List<IndexIssueResponse> findAllIssues(IndexIssuesRequest request) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<IssuesModel> criteriaQuery = criteriaBuilder.createQuery(IssuesModel.class);
        Root<IssuesModel> issuesRoot = criteriaQuery.from(IssuesModel.class);

        criteriaQuery.select(issuesRoot);
        criteriaQuery.where(criteriaBuilder.equal(issuesRoot.get("localityId"), request.getLocalityId()));

        if (request.getStatus() != null) {
            criteriaQuery.where(criteriaBuilder.equal(issuesRoot.get("status"), request.getStatus()));
        }
        if (request.getOrderBy() != null) {
            criteriaQuery.orderBy(criteriaBuilder.desc(issuesRoot.get(request.getOrderBy())));
        }

        Query qr = session.createQuery(criteriaQuery);

        if (request.getLimit() != null) {
            qr.setMaxResults(request.getLimit());
            if (request.getPage() != null) {
                qr.setFirstResult(calculateOffset(request.getPage(), request.getLimit()));
                qr.setMaxResults(request.getLimit());
            }
        }

        List<IssuesModel> rawResponse = qr.getResultList();
        List<IndexIssueResponse> response = rawResponse.stream().map(
                issue -> new IndexIssueResponse(
                        issue.getId(),
                        issue.getTitle(),
                        issue.getDescription(),
                        issue.getStatus(),
                        issue.getCreatedAt(),
                        issue.getImageUrl(),
                        issue.getUser().getUsername()
                )
        ).collect(Collectors.toList());

        return response;
    }

    @Override
    @Transactional
    public IssuesModel findIssueById(Long issueId) {
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<IssuesModel> findIssueByIdQuery = session.createQuery(
                "select i from IssuesModel i where i.id = :issueId", IssuesModel.class
        );
        findIssueByIdQuery.setParameter("issueId", issueId);
        IssuesModel issue = findIssueByIdQuery.getSingleResult();
        session.close();

        return issue;
    }

    @Override
    @Transactional
    public IssuesModel saveIssue(PostIssuesRequest request) {

        Session session = entityManager.unwrap(Session.class);

        Optional<UserModel> user = userRepository.findById(request.getUserId());
        IssuesModel newIssue = new IssuesModel(
                request.getTitle(),
                request.getDescription(),
                request.getStatus(),
                request.getImageUrl(),
                request.getLocalityId(),
                null
        );

        if (user.isPresent()) {
            newIssue.setUser(user.get());
            session.persist(newIssue);
            session.flush();
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return newIssue;
    }

    @Override
    @Transactional
    @Modifying
    public IssuesModel patchIssue(PatchIssueRequest request) {
        Session session = entityManager.unwrap(Session.class);

        // Separate query
        String update = "UPDATE IssuesModel ";
        String set = "set " + request.getKey() + " = :value";
        String where = " where id = :issueId";
        //Combine query
        Query updateQuery = session.createQuery(
                update + set + where
        );
        // `status` column should contain only ENUM values
        if (request.getKey().equals("status")) {
            // Check if valid ENUM
            boolean isEnum = checkIsEnum(request);
            if (isEnum) {
                updateQuery.setParameter("value", IssueStatuses.valueOf(request.getValue()));
            }
        // Nope, it's not an ENUM, it's a string
        } else {
            updateQuery.setParameter("value", request.getValue());
        }
        // Set last required param
        updateQuery.setParameter("issueId", request.getIssueId());
        updateQuery.executeUpdate();

        // TODO: return real Issue model
        return new IssuesModel();
    }

    private static boolean checkIsEnum(PatchIssueRequest request) {
        for (IssueStatuses statuses : IssueStatuses.values()) {
            if (statuses.name().equals(request.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static int calculateOffset(int page, int limit) {
        return ((limit * page) - limit);
    }

}
