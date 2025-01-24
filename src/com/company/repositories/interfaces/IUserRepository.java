package com.company.repositories.interfaces;

import com.company.models.User;

import java.util.List;

public interface IUserRepository {
    int createUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
    boolean deleteUser(int id);
    boolean updateUser(int id, String nickname);
}
