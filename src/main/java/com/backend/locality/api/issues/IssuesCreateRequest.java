package com.backend.locality.api.issues;

public class IssuesCreateRequest {
    private String title;
    private String description;
    private String imageUrl;
    private IssueStatuses status;
    private Long localityId;
    private Long userId;

    public IssuesCreateRequest(String title, String description, String imageUrl, IssueStatuses status, Long localityId, Long userId) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.localityId = localityId;
        this.userId = userId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public IssueStatuses getStatus() {
        return status;
    }

    public void setStatus(IssueStatuses status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }
}
