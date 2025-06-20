package com.project.capstone.service.userinfo;

import com.project.capstone.dto.userinfo.UserInfoRequestDto;
import com.project.capstone.dto.userinfo.UserInfoUpdateRequestDto;

public interface UserInfoServiceInterface {
    void saveUserInfo (UserInfoRequestDto userInfoRequestDto);
    void updateUserInfo (UserInfoUpdateRequestDto userInfoUpdateRequestDto);
}
