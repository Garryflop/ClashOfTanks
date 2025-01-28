package com.company.controllers.interfaces;

public interface IUserController {
    String createUser(String nickname);
    String getUser(int id);
    String getAllUsers();
    String deleteUser(int id);
    String updateUser(int id, String nickname);
    String updateUser(int id, int wins, int games_played);
}
