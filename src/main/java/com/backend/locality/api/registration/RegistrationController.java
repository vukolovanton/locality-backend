package com.backend.locality.api.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/registration")
public class RegistrationController {
    private RegistrationService registrationService;

    public String register(
            @RequestBody RegistrationRequest request
    ) {
        return registrationService.register(request);
    }
}
