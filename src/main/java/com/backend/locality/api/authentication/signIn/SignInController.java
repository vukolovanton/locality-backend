package com.backend.locality.api.authentication.signIn;

import com.backend.locality.api.users.UserDetailsImpl;
import com.backend.locality.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

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
                            roles,
                            jwt
                        )
                );
    }
}
