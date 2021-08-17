package com.backend.locality.api.issues.interfaces;

import com.backend.locality.api.issues.*;

import java.util.List;

public interface IIssues {
    List<IndexIssueResponse> findAllIssues(IndexIssuesRequest request);
    IssuesModel findIssueById(Long issueId);
    IssuesModel saveIssue(IssuesCreateRequest issue);

    IssuesModel patchIssue(PatchIssueRequest request);
}
