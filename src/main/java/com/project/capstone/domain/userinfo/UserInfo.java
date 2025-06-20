package com.project.capstone.domain.userinfo;


import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "user_info")
public class UserInfo {

    @Id
    private final String userId;

    //아래 개인정보들 추가

    public UserInfo(String userId) {
        this.userId = userId;
    }
}
