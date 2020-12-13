package com.example.Booking.room.model;


public class User {

    private int id;
    private String username;
    private String passW;

    public User() {}

    public User(int id, String username, String passW) {
        this.id = id;
        this.username = username;
        this.passW = passW;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassW() {
        return passW;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassW(String passW) {
        this.passW = passW;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", passWord='" + passW + '\'' +
                '}';
    }
}
