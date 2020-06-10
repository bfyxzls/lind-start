package com.lind.start.test.model;

public class User {
    private String id;
    private String username;
    private String infomartion;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", infomartion='" + infomartion + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getInfomartion() {
        return infomartion;
    }

    public void setInfomartion(String infomartion) {
        this.infomartion = infomartion;
    }
}
