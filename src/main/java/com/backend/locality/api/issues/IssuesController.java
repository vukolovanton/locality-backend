package com.backend.locality.api.issues;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/issues")
public class IssuesController {
    private final Issues issuesService;

    @RequestMapping(method = RequestMethod.GET)
    public List<IssuesModel> getAllIssues() {
        return issuesService.findAllIssues();
    }

    @RequestMapping(method = RequestMethod.POST)
    public IssuesModel saveIssue(@RequestBody IssuesModel issue) {
        return issuesService.saveIssue(issue);
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public IssuesModel getIssueById(@PathVariable Long issueId) {
        return issuesService.findIssueById(issueId);
    }
}
