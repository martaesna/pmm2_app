package com.example.myapplication.data.model;

import java.util.ArrayList;

public class User {
    private String name;
    private String color;
    private ArrayList<Device> devices;

    public User(String name, String color, ArrayList<Device> devices) {
        this.name = name;
        this.color = color;
        this.devices = devices;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }
}
