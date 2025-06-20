package com.project.capstone.service.user;

import com.project.capstone.dto.user.UserUpdateRequestDto;
import com.project.capstone.domain.user.User;

public interface UserUpdateServiceInterface {
    void updateUser(UserUpdateRequestDto userUpdateRequestDto);
    public User getUser();
}
