package com.project.capstone.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private final String userId;
    //lombok의 getter, setter의 (AccessLevel.NONE) 으로 비밀번호는 접근이 불가하게 설정
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private String password;
    private String name;
    private int age;
    private String email;
    private String phoneNum;
    private int userHeight;
    private String jwtRefreshToken;


    public User(String userId, String password, String name, int age, String email, String phoneNum, int userHeight) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNum = phoneNum;
        this.userHeight = userHeight;
    }

    /*
    비번 변경 구현헤야함 -> 아이디 인증하고, 이전 비번 인증이 되야 할 수 있게
    public void changePassword(){}
    */
}
