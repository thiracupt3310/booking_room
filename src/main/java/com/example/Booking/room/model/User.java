package com.example.Booking.room.model;


public class User {

    private String firstname;
    private String surname;
    private String username;
    private String passW;


    public User() {}

    public User(String firstname, String surname, String username, String passW) {
        this.firstname = firstname;
        this.surname = surname;
        this.username = username;
        this.passW = passW;

    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassW() {
        return passW;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
                "firstname='" + firstname + '\'' +
                ", lastname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", passW='" + passW + '\'' +
                '}';
    }
}
