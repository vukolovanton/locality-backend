package com.backend.locality.api.authentication.signIn;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignInResponse {
    private Long id;
    private Long localityId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private String token;
    private String type = "Bearer";

    public SignInResponse(Long id, Long localityId, String username, String email, String firstName, String lastName, List<String> roles, String token) {
        this.id = id;
        this.localityId = localityId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
        this.token = token;
    }
}


