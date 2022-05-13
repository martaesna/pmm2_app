package com.example.myapplication.data.model;

import java.util.ArrayList;

public class User {
    private int userID;
    private String name;
    private String color;
    private int numDevices;
    private ArrayList<Device> devices;

    public User(int userID, String name, String color, ArrayList<Device> devices) {
        this.userID = userID;
        this.name = name;
        this.color = color;
        this.devices = devices;
    }

    public User(int userID, String name, String color) {
        this.userID = userID;
        this.name = name;
        this.color = color;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getNumDevices() {
        return numDevices;
    }

    public void setNumDevices(int numDevices) {
        this.numDevices = numDevices;
    }
}
