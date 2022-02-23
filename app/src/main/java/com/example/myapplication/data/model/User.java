package com.example.myapplication.data.model;

public class User {
    private String id;
    private String name;
    private String username;
    private String color;

    public User (String id, String name, String username, String color) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
