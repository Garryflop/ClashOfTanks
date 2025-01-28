package com.company.controllers;

import com.company.models.User;
import com.company.controllers.interfaces.IUserController;
import com.company.repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    public String createUser(String nickname) {
        User user = new User(nickname);

        int created = repo.createUser(user);
        if (created > 0) {
            return created + "";
        }
        return "0";
    }

    public String getUser(int id) {
        User user = repo.getUser(id);

        return (user == null ? "User was not found!" : user.toString());
    }

    public String getAllUsers() {
        List<User> users = repo.getAllUsers();

        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user).append("\n");
        }

        return response.toString();
    }

    public String deleteUser(int id) {
        User user = repo.getUser(id);
        boolean deleted = repo.deleteUser(id);
        return (deleted ? "User was deleted!" : "User deletion was failed!");
    }
    public String updateUser(int id, String nickname) {
        User user = repo.getUser(id);
        boolean updated = repo.updateUser(id, nickname);
        return (updated ? "User was updated!" : "User update was failed!");
    }

    public String updateUser(int id, int wins, int games_played) {
        User user = repo.getUser(id);
        boolean updated = repo.updateUser(id, wins, games_played);
        return (updated ? "User was updated!" : "User update was failed!");
    }
}