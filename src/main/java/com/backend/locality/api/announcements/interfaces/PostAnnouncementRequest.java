package com.backend.locality.api.announcements.interfaces;

public class PostAnnouncementRequest {
    private Long userId;
    private Long localityId;
    private String title;
    private String description;
    private String imageUrl;
    private AnnouncementsStatus status;
    private boolean isPinned;

    public PostAnnouncementRequest(Long userId, Long localityId, String title, String description, String imageUrl, AnnouncementsStatus status, boolean isPinned) {
        this.userId = userId;
        this.localityId = localityId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.isPinned = isPinned;
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

    public AnnouncementsStatus getStatus() {
        return status;
    }

    public void setStatus(AnnouncementsStatus status) {
        this.status = status;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
