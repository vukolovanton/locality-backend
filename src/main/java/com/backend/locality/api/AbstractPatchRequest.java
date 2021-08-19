package com.backend.locality.api;

public class AbstractPatchRequest {
    private Long entityId;
    private String key;
    private String value;

    public AbstractPatchRequest(Long entityId, String key, String value) {
        this.entityId = entityId;
        this.key = key;
        this.value = value;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
