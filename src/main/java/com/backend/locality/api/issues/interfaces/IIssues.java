package com.backend.locality.api.issues.interfaces;

import com.backend.locality.api.issues.IssuesCreateRequest;
import com.backend.locality.api.issues.IssuesModel;

import java.util.List;

public interface IIssues {
    List<IssuesModel> findAllIssues();
    IssuesModel findIssueById(Long issueId);
    IssuesModel saveIssue(IssuesCreateRequest issue);
}
