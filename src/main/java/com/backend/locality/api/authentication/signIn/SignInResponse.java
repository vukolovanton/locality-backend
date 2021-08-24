package com.backend.locality.api.authentication.signIn;

import com.backend.locality.api.role.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {
    private Long id;
    private Long localityId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
    private String token;
    private String type = "Bearer";

    public SignInResponse(Long id, Long localityId, String username, String email, String firstName, String lastName, Role role, String token) {
        this.id = id;
        this.localityId = localityId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.token = token;
    }
}


