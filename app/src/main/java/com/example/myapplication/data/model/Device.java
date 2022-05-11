package com.example.myapplication.data.model;

public class Device {
    private int deviceID;
    private String id;
    private String objectName;

    public Device(int deviceID, String id, String objectName) {
        this.deviceID = deviceID;
        this.id = id;
        this.objectName = objectName;
    }

    public Device() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }
}
