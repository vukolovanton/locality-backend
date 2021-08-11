package com.backend.locality.api.locality;

public class LocalityCreationDTO {

    private String title;

    private String description;

    private String city;

    private String street;

    private Long userId;

    public LocalityCreationDTO(String title, String description, String city, String street, Long userId) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.street = street;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
