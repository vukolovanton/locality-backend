package com.backend.locality.api.issues;

public class IndexIssueResponse {
    private Long id;
    private String title;
    private String description;
    private IssueStatuses status;
    private String imageUrl;
    private String username;

    public IndexIssueResponse(Long id, String title, String description, IssueStatuses status, String imageUrl, String username) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IssueStatuses getStatus() {
        return status;
    }

    public void setStatus(IssueStatuses status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
