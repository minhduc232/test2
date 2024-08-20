package com.example.baitest2.Entity;

import lombok.Data;

@Data
public class LoginResponse {
    public String accessToken;
    private String tokenType = "Bearer";

    public LoginResponse(String accessToken)
    {
        this.accessToken = accessToken;
    }
}
