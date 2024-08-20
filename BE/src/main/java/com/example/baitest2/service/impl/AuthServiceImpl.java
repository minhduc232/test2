package com.example.baitest2.service.impl;


import com.example.baitest2.Repository.UserRepository;
import com.example.baitest2.dto.AuthResponse;
import com.example.baitest2.dto.LoginCommand;
import com.example.baitest2.dto.RegisterCommand;
import com.example.baitest2.entity.User;
import com.example.baitest2.service.AuthService;
import com.example.baitest2.service.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.expires-in}")
    private Long expiresIn;
    @Value("${jwt.refresh-expires-in}")
    private Long refreshExpiresIn;

    private final JwtService jwtService;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;

    @Override
    public AuthResponse login(LoginCommand command) {
        var token = new UsernamePasswordAuthenticationToken(
                command.usernameOrEmail(),
                command.password());
        var authentication = authManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var username = ((User) authentication.getPrincipal()).getUsername();
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(username))
                .refreshToken(jwtService.generateRefreshToken(username))
                .expiresIn(expiresIn)
                .refreshExpiresIn(refreshExpiresIn)
                .build();
    }

    @Override
    public AuthResponse register(RegisterCommand command) {
        if (userRepository.existsByUsername(command.username())) {
            throw new RuntimeException("User not found");
        }
        var user = modelMapper.map(command, User.class);
        var encodedPassword = passwordEncoder.encode(command.password());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return AuthResponse.builder()
                .accessToken(jwtService.generateToken(user.getUsername()))
                .refreshToken(jwtService.generateRefreshToken(user.getUsername()))
                .expiresIn(expiresIn)
                .refreshExpiresIn(refreshExpiresIn)
                .build();
    }

}
