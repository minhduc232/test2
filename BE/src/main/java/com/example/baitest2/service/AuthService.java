package com.example.baitest2.service;


import com.example.baitest2.dto.AuthResponse;
import com.example.baitest2.dto.LoginCommand;
import com.example.baitest2.dto.RegisterCommand;

public interface AuthService {

    AuthResponse login(LoginCommand command);

    AuthResponse register(RegisterCommand command);

}
