package com.backend.locality.api.authentication.signIn;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignInResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public SignInResponse(String token, Long id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
