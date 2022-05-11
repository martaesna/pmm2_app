package com.example.myapplication.data.model;

import java.util.ArrayList;

public class Account {
    private int accountID;
    private String name;
    private String password;
    private ArrayList<User> users;

    public Account(int accountID, String name, String password, ArrayList<User> users) {
        this.accountID = accountID;
        this.name = name;
        this.password = password;
        this.users = users;
    }

    public Account(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
