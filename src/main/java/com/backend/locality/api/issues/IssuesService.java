package com.backend.locality.api.issues;

import com.backend.locality.api.issues.interfaces.IIssuesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssuesService implements IIssuesService {
    private final IssuesRepository issuesRepository;

    @Override
    public List<IssuesModel> findAllIssues() {
        return issuesRepository.findAllIssues();
    }

    @Override
    public IssuesModel findIssueById(int issueId) {
        return issuesRepository.findIssueById(issueId);
    }

    @Override
    public IssuesModel saveIssue(IssuesModel issue) {
        return issuesRepository.saveIssue(issue);
    }
}
