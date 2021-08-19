package com.backend.locality.api.issues;

import com.backend.locality.api.issues.interfaces.IIssues;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IssuesService implements IIssues {
    private final IssuesRepository issuesRepository;

    @Override
    public List<IndexIssueResponse> findAllIssues(IndexIssuesRequest request) {
        return issuesRepository.findAllIssues(request);
    }

    @Override
    public IssuesModel findIssueById(Long issueId) {
        return issuesRepository.findIssueById(issueId);
    }

    @Override
    public IssuesModel saveIssue(PostIssuesRequest issue) {
        return issuesRepository.saveIssue(issue);
    }

    @Override
    public IssuesModel patchIssue(PatchIssueRequest request) {
        return issuesRepository.patchIssue(request);
    }
}
