package com.backend.locality.api.issues;

import com.backend.locality.api.issues.interfaces.IIssues;
import com.backend.locality.api.users.UserModel;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
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

        // Separate query parts into string so we can conditionally combine them
        String select = "select new com.backend.locality.api.issues.IndexIssueResponse";
        String items = "(i.id, i.title, i.description, i.status, i.createdAt, i.imageUrl, i.user.username)";
        String from = "from IssuesModel i";
        String where = "where i.localityId = :localityId";

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

        if (request.getStatus() != null) {
            String condition = "and i.status = :status";
            sb.append(condition);
        }


        // Create query
        TypedQuery<IndexIssueResponse> findAllIssues = session.createQuery(sb.toString(), IndexIssueResponse.class);
        findAllIssues.setParameter("localityId", request.getLocalityId());

        if (request.getStatus() != null) {
            findAllIssues.setParameter("status", request.getStatus());
        }

        // If request has limit, use it
        if (request.getLimit() != null) {
            findAllIssues.setMaxResults(request.getLimit());
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
    public IssuesModel saveIssue(IssuesCreateRequest request) {

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
    public IssuesModel patchIssue(PatchIssueRequest request) {
        Session session = entityManager.unwrap(Session.class);

        IssuesModel issue = findIssueById(request.getIssueId());
        issue.setImageUrl(request.getImageUrl());
        session.persist(issue);

        return issue;
    }
}
