package com.company.repositories;

import com.company.data.interfaces.IDB;
import com.company.models.User;
import com.company.repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;  // Dependency Injection

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public int createUser(User user) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "INSERT INTO players(nickname) VALUES (?) RETURNING id";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, user.getNickname());

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int generatedId = rs.getInt("id");
                    System.out.println("User created: " + user.getNickname() + " with ID: " + generatedId);
                    return generatedId;
                }
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return 0;
    }

    @Override
    public User getUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id, nickname, wins, games_played, win_rate FROM players WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getInt("wins"),
                        rs.getInt("games_played"),
                        rs.getDouble("win_rate"));
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "SELECT id,nickname,wins,games_played,win_rate FROM players";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("nickname"),
                        rs.getInt("wins"),
                        rs.getInt("games_played"),
                        rs.getDouble("win_rate"));
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM players WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, id);

            int rowsDeleted = st.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateUser(int id, String nickname) {
        Connection con = null;
        User user = getUser(id);
        try {
            con = db.getConnection();
            String sql = """
                UPDATE players 
                SET nickname = ?, 
                    wins = ?, 
                    games_played = ?, 
                    win_rate = ? 
                WHERE id = ?""";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, nickname);
            st.setInt(2, user.getWins());
            st.setInt(3, user.getGames_played());
            st.setDouble(4, user.getWin_rate());
            st.setInt(5, user.getId());

            int rowsUpdated = st.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }
}
