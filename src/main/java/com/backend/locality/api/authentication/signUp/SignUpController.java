package com.backend.locality.api.authentication.signUp;

import com.backend.locality.api.authentication.MessageResponse;
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
                    .body(new MessageResponse("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        UserModel user = new UserModel(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));

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
                    case "contractor":
                        Role adminRole = roleRepository
                                .findByName(RolesEnum.CONTRACTOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role CONTRACTOR is not found"));
                        roles.add(adminRole);

                        break;
                    case "supervisor":
                        Role modRole = roleRepository
                                .findByName(RolesEnum.SUPERVISOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role SUPERVISOR is not found"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepository
                                .findByName(RolesEnum.USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
