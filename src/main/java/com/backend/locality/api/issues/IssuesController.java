package com.backend.locality.api.issues;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public IssuesModel saveIssue(@RequestBody IssuesCreateRequest issue) {
        return issuesService.saveIssue(issue);
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public IssuesModel getIssueById(@PathVariable Long issueId) {
        return issuesService.findIssueById(issueId);
    }
}
