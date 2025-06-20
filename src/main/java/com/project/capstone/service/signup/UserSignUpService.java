package com.project.capstone.service.signup;

import com.project.capstone.domain.user.User;
import com.project.capstone.dto.signup.UserSignUpRequestDto;
import com.project.capstone.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// 클라이언트의 회원가입 요청을 받아서 User 객체를 만듬, 비밀번호는 BCryptPasswordEncoder 이용해서 해싱
@Service
@RequiredArgsConstructor
public class UserSignUpService implements UserSignUpServiceInterface {
    //private User user = new User();
    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    public void newUser(UserSignUpRequestDto userSignUpRequestDto) {
        User newUser = new User(userSignUpRequestDto.getUserId(),
                encoder.encode(userSignUpRequestDto.getPassword()),
                userSignUpRequestDto.getName(),
                userSignUpRequestDto.getAge(),
                userSignUpRequestDto.getEmail(),
                userSignUpRequestDto.getPhoneNum(),
                userSignUpRequestDto.getUserHeight()
        );
        userRepository.save(newUser);
        System.out.println("완료!");
    }
}
