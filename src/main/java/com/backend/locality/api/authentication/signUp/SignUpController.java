package com.backend.locality.api.authentication.signUp;

import com.backend.locality.api.role.Role;
import com.backend.locality.api.users.UserModel;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class SignUpController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new SignUpResponse("Username" + signupRequest.getUsername() + "already exists", null));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new SignUpResponse("Email" + signupRequest.getEmail() + "already exists", null));
        }

        UserModel user = new UserModel(
                signupRequest.getUsername(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getEmail(),
                signupRequest.getRoles()
        );

        UserModel newUser = userRepository.save(user);

        // For now I want response to have only SUCCESS message and ID (not the token)
        // Thinking about email confirmation...
        return ResponseEntity.ok(new SignUpResponse("SUCCESS", newUser.getId()));
    }
}
