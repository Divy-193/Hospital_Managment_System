package com.hospital.controller;

import com.hospital.dto.LoginRequestDto;
import com.hospital.dto.LoginResponseDto;
import com.hospital.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(AuthService.login(loginRequestDto));
    }
}
