package com.example.announcement_procedures_automation_projectoop;

public class User {
    private String name;
    private String surname;
    private String ID;
    private String password;

    public User(String name, String surname, String ID, String password) {
        this.name = name;
        this.surname = surname;
        this.ID = ID;
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
