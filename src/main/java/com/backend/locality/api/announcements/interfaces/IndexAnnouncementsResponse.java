package com.backend.locality.api.announcements.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class IndexAnnouncementsResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private boolean isPinned;
    private AnnouncementsStatus status;
    private LocalDateTime createdAt;
    private String username;
    private String email;

    public IndexAnnouncementsResponse(Long id, String title, String description, String imageUrl, boolean isPinned, AnnouncementsStatus status, LocalDateTime createdAt, String username, String email) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isPinned = isPinned;
        this.status = status;
        this.createdAt = createdAt;
        this.username = username;
        this.email = email;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getIsPinned() {
        return isPinned;
    }

    public void setIsPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public AnnouncementsStatus getStatus() {
        return status;
    }

    public void setStatus(AnnouncementsStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
