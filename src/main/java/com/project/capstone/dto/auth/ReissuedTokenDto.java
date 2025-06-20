package com.project.capstone.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReissuedTokenDto {
    private String accessToken;
}
