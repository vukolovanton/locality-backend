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
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class IssuesRepository implements IIssues {
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<IndexIssueResponse> findAllIssues(IndexIssuesRequest request) {
        Session session = entityManager.unwrap(Session.class);
        // TODO: Simplify this
        // Separate query parts into string so we can conditionally combine them
        String select = "select new com.backend.locality.api.issues.IndexIssueResponse";
        String items = "(i.id, i.title, i.description, i.status, i.createdAt, i.imageUrl, i.user.username)";
        String from = "from IssuesModel i";
        String where = "where i.localityId = :localityId";
        // Combine strings
        StringBuilder sb = new StringBuilder();
        List<String> conditions = Arrays.asList(select, items, from, where);
        for (String s : conditions) {
            sb.append(s);
            sb.append(" ");
        }
        // If request contains orderBy value, apply it to query
        if (request.getOrderBy() != null) {
            String condition = "order by i." + request.getOrderBy() + " DESC";
            sb.append(condition);
        }
        // Add status
        if (request.getStatus() != null) {
            String condition = "and i.status = :status";
            sb.append(condition);
        }
        // Create query
        TypedQuery<IndexIssueResponse> findAllIssues = session.createQuery(sb.toString(), IndexIssueResponse.class);
        findAllIssues.setParameter("localityId", request.getLocalityId());
        // Filer by status
        if (request.getStatus() != null) {
            findAllIssues.setParameter("status", request.getStatus());
        }
        // If request has limit, use it
        if (request.getLimit() != null) {
            findAllIssues.setMaxResults(request.getLimit());
            if (request.getPage() != null) {
                findAllIssues.setFirstResult(calculateOffset(request.getPage(), request.getLimit()));
                findAllIssues.setMaxResults(request.getLimit());
            }

        }
        return findAllIssues.getResultList();
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
