package com.project.capstone.service.auth;

import com.project.capstone.domain.user.User;
import com.project.capstone.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLogOutService implements UserLogOutServiceInterface {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity logout() {
        //Dto로 받는게 보안 관점에선 별로임
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUserId(userId);
        user.setJwtRefreshToken(null);
        userRepository.save(user);

        ResponseCookie deleteCookie = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(true)
                .maxAge(0)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body("로그아웃 완료");
    }
}
