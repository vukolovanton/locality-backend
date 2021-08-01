package com.backend.locality.api.users;

import com.backend.locality.api.role.Role;
import com.backend.locality.api.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
//@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel saveUser(UserModel user) {
        log.info("Saving new user to the database");
        return userRepository.save(user);
    }

    @Override
    public List<UserModel> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserModel findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserModel findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserModel register(UserModel user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        UserModel registeredUser = userRepository.save(user);
        log.info("User: {} was successfully registered", registeredUser.getEmail());

        return registeredUser;
    }

}
