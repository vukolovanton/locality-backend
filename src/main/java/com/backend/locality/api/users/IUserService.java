package com.backend.locality.api.users;

import java.util.List;

public interface IUserService {
    UserModel saveUser(UserModel user);
    List<UserModel> getUsers();
    UserModel findByEmail (String email);
    UserModel findByUsername (String username);
    UserModel findById (Long id);
    void delete(Long id);
    UserModel register(UserModel user);
}
