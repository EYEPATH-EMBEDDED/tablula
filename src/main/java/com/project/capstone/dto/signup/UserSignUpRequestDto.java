package com.project.capstone.dto.signup;

import lombok.Data;

//회원 가입 DTO
@Data
public class UserSignUpRequestDto {
    private String userId;
    //로그인 request 부분도 비밀번호 getter 없애야지 -> 라고 생각했는데.. DTO는 그저 데이터를 주고 받는 객체이지
    //실제로 보안을 담당하지 않음 여기선 getter가 가능하게 해야함..
    private String password;
    private String name;
    private int age;
    private String email;
    private String phoneNum;
    private int userHeight;
}
