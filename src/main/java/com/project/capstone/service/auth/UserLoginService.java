package com.project.capstone.service.auth;

import com.project.capstone.domain.user.User;
import com.project.capstone.dto.auth.UserLoginRequestDto;
import com.project.capstone.dto.auth.UserLoginResponseDto;
import com.project.capstone.dto.auth.RepositoryPasswordReturnDto;
import com.project.capstone.repository.user.UserRepository;
import com.project.capstone.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserLoginService implements UserLoginServiceInterface {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity login(UserLoginRequestDto userLoginRequestDto) {
        String requestId = userLoginRequestDto.getUserId();
        String requestPassword = userLoginRequestDto.getPassword();
        //이 아래에 일치하는지 확인도 로그인
        //이게 아이디로 비교하지말고 어차피 클라이언트가 입력한 아이디로 조회했을 때 비번이 없으면(Null 반환) 애초에 없던 계정이니
        //그냥 비번으로 조회하는게 편함
        RepositoryPasswordReturnDto repositoryPasswordReturnDto = userRepository.findOnlyPasswordById(requestId);

        //아디 비번없으면 예외로 넘기고 있으면 토큰 들어있는 LoginResponseDto를 넘겨주자
        if(repositoryPasswordReturnDto.getPassword() == null || !bcryptPasswordEncoder.matches(requestPassword, repositoryPasswordReturnDto.getPassword())) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 일치하지 않습니다");
        } else {
            //여기에 로그인한 유저에게 Refresh 토큰을 넘겨주는거 추가하자
            User user = userRepository.findByUserId(requestId);
            String refreshToken = jwtTokenProvider.generateRefreshToken(requestId);
            user.setJwtRefreshToken(jwtTokenProvider.generateRefreshToken(requestId));
            userRepository.save(user);

            //쿠키 생성
            ResponseCookie refreshTokenCookie = makeRefreshCookie(refreshToken);

            //ResponseEntity로 변환
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                    .body(new UserLoginResponseDto(requestId, jwtTokenProvider.generateAccessToken(requestId)));
        }
    }

    public ResponseCookie makeRefreshCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(7*24*60*60)
                .sameSite("Strict")
                .build();
    }
}
