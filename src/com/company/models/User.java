package com.company.models;

public class User {
    private int id;
    private static int idCounter = 0;
    private String nickname;
    private int wins;
    private int games_played;
    private double win_rate;
    public User() {
        setId(++idCounter);
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getGames_played() {
        return games_played;
    }

    public void setGamesPlayed(int games_played) {
        this.games_played = games_played;
    }

    public double getWin_rate() {
        return win_rate;
    }

    public void setWinRate(double win_rate) {
        this.win_rate = win_rate;
    }

    public User(String nickname) {
        this();
        setNickname(nickname);
    }

    public User(int id, String name, int wins,int games_played, double win_rate) {
        setId(id);
        setNickname(name);
        setWins(wins);
        setGamesPlayed(games_played);
        setWinRate(win_rate);
    }

    private void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setNickname(String nickname) {
        if (nickname == null){
            this.nickname = "Player" + getId();
        }
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", wins=" + wins +
                ", games_played=" + games_played +
                ", win_rate=" + win_rate +
                '}';
    }

    public String getNickname() {
        return nickname;
    }
}
