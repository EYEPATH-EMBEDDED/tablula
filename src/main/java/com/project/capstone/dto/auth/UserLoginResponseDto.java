package com.project.capstone.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

//로그인 응답 DTO
@Getter
@AllArgsConstructor
public class UserLoginResponseDto {
    private String userId;
    //로그인 성공 시 클라이언트에게 제공하는 토큰(accessToken)
    private String accessToken;
}
