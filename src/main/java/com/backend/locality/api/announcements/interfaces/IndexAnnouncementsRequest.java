package com.backend.locality.api.announcements.interfaces;

public class IndexAnnouncementsRequest {
    private Long localityId;
    private Integer limit;
    private Integer page;
    private AnnouncementsStatus status;

    public Long getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public AnnouncementsStatus getStatus() {
        return status;
    }

    public void setStatus(AnnouncementsStatus status) {
        this.status = status;
    }
}
