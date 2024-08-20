package com.example.baitest2.Controller;


import com.example.baitest2.dto.LoginCommand;
import com.example.baitest2.dto.RegisterCommand;
import com.example.baitest2.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login",
            consumes = {"application/x-www-form-urlencoded"},
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> login(@RequestParam MultiValueMap<String, String> mapParam) {
        var command = LoginCommand.builder()
                .usernameOrEmail(mapParam.getFirst("username_or_email"))
                .password(mapParam.getFirst("password"))
                .build();
        return ResponseEntity.ok(authService.login(command));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterCommand command) {
        System.out.println(command);
        return ResponseEntity.ok(authService.register(command));
    }

}
