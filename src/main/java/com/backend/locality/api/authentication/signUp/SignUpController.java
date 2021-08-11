package com.backend.locality.api.authentication.signUp;

import com.backend.locality.api.authentication.MessageResponse;
import com.backend.locality.api.locality.LocalityModel;
import com.backend.locality.api.role.Role;
import com.backend.locality.api.role.RoleRepository;
import com.backend.locality.api.role.RolesEnum;
import com.backend.locality.api.users.UserModel;
import com.backend.locality.api.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.backend.locality.api.role.RolesEnum.CONTRACTOR;
import static com.backend.locality.api.role.RolesEnum.SUPERVISOR;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/v1/auth")
@AllArgsConstructor
public class SignUpController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username" + signupRequest.getUsername() + "already exists"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email" + signupRequest.getEmail() + "already exists"));
        }

        UserModel user = new UserModel(
                signupRequest.getUsername(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getEmail()
        );

        Set<String> reqRoles = signupRequest.getRoles();
        List<Role> roles = new ArrayList<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(RolesEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "contractor" -> {
                        Role adminRole = roleRepository
                                .findByName(RolesEnum.CONTRACTOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role CONTRACTOR is not found"));
                        roles.add(adminRole);
                    }
                    case "supervisor" -> {
                        Role modRole = roleRepository
                                .findByName(RolesEnum.SUPERVISOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role SUPERVISOR is not found"));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository
                                .findByName(RolesEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        // For now I want response to have only SUCCESS message
        // Thinking about email confirmation...
        return ResponseEntity.ok(new MessageResponse("SUCCESS"));
    }
}
