package com.backend.locality.api.authentication.signUp;

import com.backend.locality.api.role.RolesEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private Set<RolesEnum> roles;
}
