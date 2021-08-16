package com.backend.locality.api.issues;

import com.backend.locality.api.issues.interfaces.IIssues;
import com.backend.locality.api.users.UserModel;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class IssuesRepository implements IIssues {
    private final EntityManager entityManager;
    private final UserRepository userRepository;

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
}
