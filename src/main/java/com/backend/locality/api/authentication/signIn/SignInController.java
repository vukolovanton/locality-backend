package com.backend.locality.api.authentication.signIn;

import com.backend.locality.api.users.UserDetailsImpl;
import com.backend.locality.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class SignInController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody SignInRequest signinRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signinRequest.getUsername(),
                        signinRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .body(
                        new SignInResponse(
                            userDetails.getId(),
                            userDetails.getLocalityId(),
                            userDetails.getUsername(),
                            userDetails.getEmail(),
                            userDetails.getFirstName(),
                            userDetails.getLastName(),
                            userDetails.getRole(),
                            jwt
                        )
                );
    }
}
