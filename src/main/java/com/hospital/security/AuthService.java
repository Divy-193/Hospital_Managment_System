package com.hospital.security;


import com.hospital.dto.LoginRequestDto;
import com.hospital.dto.LoginResponseDto;
import com.hospital.dto.SignupRequestDto;
import com.hospital.entity.User;
import com.hospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal(); // ?????why?

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token,user.getId());
    }

    public  SignupRequestDto signup(LoginRequestDto signupRequestDto) throws IllegalAccessException {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if (user != null) throw new IllegalAccessException("User Already Exist");

         user = userRepository.save(User.builder()
                 .username(signupRequestDto.getUsername())
                 .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                 .build()
         );
         return new SignupRequestDto(user.getId(), user.getUsername());
    }
}
