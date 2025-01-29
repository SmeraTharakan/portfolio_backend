package com.example.portfolio.responseModel;

import lombok.Data;

@Data

public class UserResponse<T> {
    private int statusCode;
    private String status;
    private String message;
    private T response;

    public UserResponse(int statusCode, String status, String message, T response) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.response = response;
    }

}
