package com.backend.locality.api.authentication.signUp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignUpResponse {
    private String message;
    private Long id;
}
