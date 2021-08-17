package com.backend.locality.api.issues.interfaces;

import com.backend.locality.api.issues.IndexIssueResponse;
import com.backend.locality.api.issues.IssuesCreateRequest;
import com.backend.locality.api.issues.IndexIssuesRequest;
import com.backend.locality.api.issues.IssuesModel;

import java.util.List;

public interface IIssues {
    List<IndexIssueResponse> findAllIssues(IndexIssuesRequest request);
    IssuesModel findIssueById(Long issueId);
    IssuesModel saveIssue(IssuesCreateRequest issue);
}
