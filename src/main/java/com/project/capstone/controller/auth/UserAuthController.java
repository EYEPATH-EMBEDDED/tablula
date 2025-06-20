package com.project.capstone.controller.auth;

import com.project.capstone.dto.auth.ReissuedTokenDto;
import com.project.capstone.dto.auth.UserLoginRequestDto;
import com.project.capstone.dto.auth.UserLoginResponseDto;
import com.project.capstone.service.auth.AccessTokenReissueService;
import com.project.capstone.service.auth.UserLogOutServiceInterface;
import com.project.capstone.service.auth.UserLoginServiceInterface;
import com.project.capstone.service.signup.UserSignUpServiceInterface;
import com.project.capstone.service.user.UserUpdateServiceInterface;
import com.project.capstone.service.userinfo.UserInfoServiceInterface;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequiredArgsConstructor
@RestController // HTTP 요청을 처리하는 컨트롤러를 의미
@RequestMapping("/auth")
public class UserAuthController {

    private final UserLoginServiceInterface loginService;
    private final UserLogOutServiceInterface userLogOutService;
    private final AccessTokenReissueService accessTokenReissueService;

    @PostMapping("/login")
    public ResponseEntity userLogIn(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        // loginservice 실행해가지고!! 이제 일치하면~~ login 그리고 토큰을 지급한다.

        //사실 return은 LoginResponseDto를 해줘야한다. -> 토큰 발급해줘야지
        //맞으면 service에서 토큰을 발급하여 LoginResponseDto에 넣고 그걸 반환

        return loginService.login(userLoginRequestDto);
    }

    @PostMapping("/logout")
    public ResponseEntity userLogOut(){

        return userLogOutService.logout();
    };

    //Access 토큰 만료시 프론트에서 /reissue로 넘겨줘야함
    @PostMapping("/reissue")
    public ReissuedTokenDto reissueAccessToken(HttpServletRequest request) {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals("refreshToken"))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(() -> new IllegalArgumentException("쿠키에 Refresh Token이 없습니다."));

        return new ReissuedTokenDto(
                accessTokenReissueService
                        .reissueAccessToken(refreshToken)
        );
    }

}
