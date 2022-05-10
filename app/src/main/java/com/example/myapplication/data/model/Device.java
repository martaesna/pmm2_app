package com.example.myapplication.data.model;

public class Device {
    private String id;
    private String objectName;

    public Device(String id, String objectName) {
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
}
