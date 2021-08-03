package com.backend.locality.api.issues;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/issues")
public class IssuesController {
    private final IssuesService issuesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<IssuesModel> getAllIssues() {
        return issuesService.findAllIssues();
    }

    @RequestMapping(method = RequestMethod.POST)
    public IssuesModel saveIssue(IssuesModel issue) {
        return issuesService.saveIssue(issue);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public IssuesModel getIssueById(int issueId) {
        return issuesService.findIssueById(issueId);
    }
}
