package com.hospital.security;


import com.hospital.dto.LoginRequestDto;
import com.hospital.dto.LoginResponseDto;
import com.hospital.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static AuthenticationManager authenticationManager;
    private static AuthUtil authUtil;

    public static LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal(); // ?????why?

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token,user.getId());
    }
}
