package com.example.baitest2.Controller;


import com.example.baitest2.Entity.LoginRequest;
import com.example.baitest2.Entity.LoginResponse;
import com.example.baitest2.Entity.User;
import com.example.baitest2.Service.UserService;
import com.example.baitest2.configuration.CustomUserDetails;
import com.example.baitest2.configuration.jwt.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    JwtTokenProvider tokenProvider;
    AuthenticationManager authenticationManager;
    UserService userService;
    PasswordEncoder passwordEncoder;
    public AuthController(JwtTokenProvider tokenProvider,
                          AuthenticationManager authenticationManager,
                          UserService userService,
                          PasswordEncoder passwordEncoder)
    {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/api/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest)
    {
        //Xac thuc user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }

    @PostMapping("/api/register")
    public ResponseEntity register(@RequestBody User newUser)
    {
        try {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            this.userService.AddUser(newUser);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
