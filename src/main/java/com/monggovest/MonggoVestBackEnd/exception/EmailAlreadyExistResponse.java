package com.monggovest.MonggoVestBackEnd.exception;

public class EmailAlreadyExistResponse {
    private String userEmail;

    public EmailAlreadyExistResponse(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
