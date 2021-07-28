package com.backend.locality.api.users;

import java.util.List;

public interface IUserService {
    UserModel saveUser(UserModel user);
    UserModel getUser(String username);
    List<UserModel> getUsers();
}
