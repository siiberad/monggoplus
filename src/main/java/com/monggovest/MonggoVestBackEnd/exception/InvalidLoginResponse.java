package com.monggovest.MonggoVestBackEnd.exception;

public class InvalidLoginResponse {
    private String userEmail;
    private String userPassword;

    public InvalidLoginResponse() {
        this.userEmail = "INVALID EMAIL";
        this.userPassword = "INVALID PASSWORD";
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}