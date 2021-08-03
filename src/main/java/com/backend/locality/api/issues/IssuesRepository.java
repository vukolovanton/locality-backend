package com.backend.locality.api.issues;

import com.backend.locality.api.issues.interfaces.IIssuesRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@AllArgsConstructor
public class IssuesRepository implements IIssuesRepository {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<IssuesModel> findAllIssues() {
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<IssuesModel> findAllIssues = session.createQuery(
                "select i from IssuesModel i", IssuesModel.class
        );
        return findAllIssues.getResultList();
    }

    @Override
    @Transactional
    public IssuesModel findIssueById(int issueId) {
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
    public IssuesModel saveIssue(IssuesModel issue) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(issue);
        session.flush();

        // Is it going to have id?
        return issue;
    }
}
