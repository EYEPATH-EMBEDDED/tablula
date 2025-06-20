package com.project.capstone.service.auth;

import com.project.capstone.dto.auth.UserLoginRequestDto;
import com.project.capstone.dto.auth.UserLoginResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserLoginServiceInterface {
    ResponseEntity login(UserLoginRequestDto userLoginRequestDto);
}
