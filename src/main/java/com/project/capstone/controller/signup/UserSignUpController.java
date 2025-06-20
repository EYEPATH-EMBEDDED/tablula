package com.project.capstone.controller.signup;

import com.project.capstone.dto.signup.UserSignUpRequestDto;
import com.project.capstone.service.signup.UserSignUpServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserSignUpController {

    private final UserSignUpServiceInterface userSignUpService;

    @PostMapping
    public ResponseEntity userSignUp(@RequestBody UserSignUpRequestDto signupRequestDtoUser) {
        //회원가입 로직 처리
        userSignUpService.newUser(signupRequestDtoUser);
        return ResponseEntity.ok("회원 가입 완료");
    }

}
