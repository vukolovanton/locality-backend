package com.backend.locality.api.authentication;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthorizationRequest {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
}
