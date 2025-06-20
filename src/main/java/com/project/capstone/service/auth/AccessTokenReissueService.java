package com.project.capstone.service.auth;

import com.project.capstone.domain.user.User;
import com.project.capstone.repository.user.UserRepository;
import com.project.capstone.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccessTokenReissueService {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public String reissueAccessToken(String refreshToken) {

        //refreshToken 유효성 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        //유저 디비에 저장된 Refresh 토큰을 꺼내서 비교
        String userId = jwtTokenProvider.getUserIdFromToken(refreshToken);
        User user = userRepository.findByUserId(userId);

        if(!refreshToken.equals(user.getJwtRefreshToken())) {
            throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
        }

        return jwtTokenProvider.generateAccessToken(userId);

    }
}
