package com.backend.locality.api.authentication.signIn;

import lombok.Data;

@Data
public class SignInRequestDTO {
    private String username;
    private String password;
}
