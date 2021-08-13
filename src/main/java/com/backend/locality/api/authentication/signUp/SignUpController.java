package com.backend.locality.api.authentication.signUp;

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
import java.util.List;
import java.util.Set;


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
                signupRequest.getEmail()
        );

        Set<RolesEnum> reqRoles = signupRequest.getRoles();
        List<Role> roles = new ArrayList<>();

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(RolesEnum.USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case CONTRACTOR -> {
                        Role adminRole = roleRepository
                                .findByName(RolesEnum.CONTRACTOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role CONTRACTOR is not found"));
                        roles.add(adminRole);
                    }
                    case SUPERVISOR -> {
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
        UserModel newUser = userRepository.save(user);

        // For now I want response to have only SUCCESS message and ID (not the token)
        // Thinking about email confirmation...
        return ResponseEntity.ok(new SignUpResponse("SUCCESS", newUser.getId()));
    }
}
