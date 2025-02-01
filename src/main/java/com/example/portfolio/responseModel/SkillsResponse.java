package com.example.portfolio.responseModel;

import lombok.Data;

@Data

public class SkillsResponse<T> {
    private int status;
    private String statusMessage;
    private String message;
    private T response;

}
