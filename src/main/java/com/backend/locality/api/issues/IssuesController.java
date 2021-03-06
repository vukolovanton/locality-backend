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
    public List<IndexIssueResponse> index(@ModelAttribute IndexIssuesRequest request) {
        return issuesService.findAllIssues(request);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    public IssuesModel patch(@RequestBody PatchIssueRequest request) {
        // TODO: Make sure only owner of issue can update it
        return issuesService.patchIssue(request);
    }

    @RequestMapping(method = RequestMethod.POST)
    public IssuesModel saveIssue(@RequestBody PostIssuesRequest issue) {
        return issuesService.saveIssue(issue);
    }

    @RequestMapping(value = "/{issueId}", method = RequestMethod.GET)
    public IssuesModel getIssueById(@PathVariable Long issueId) {
        return issuesService.findIssueById(issueId);
    }
}
