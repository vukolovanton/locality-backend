package com.backend.locality.api.issues.interfaces;

import com.backend.locality.api.issues.IssuesModel;

import java.util.List;

public interface IIssuesService {
    List<IssuesModel> findAllIssues();
    IssuesModel findIssueById(int issueId);
    IssuesModel saveIssue(IssuesModel issue);
}