package com.project.capstone.service.userinfo;

import com.project.capstone.domain.userinfo.UserInfo;
import com.project.capstone.dto.userinfo.UserInfoRequestDto;
import com.project.capstone.dto.userinfo.UserInfoUpdateRequestDto;
import com.project.capstone.repository.userinfo.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserInfoService implements UserInfoServiceInterface {

    private final UserInfoRepository userInfoRepository;

    @Override
    public void saveUserInfo(UserInfoRequestDto userInfoRequestDto) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = new UserInfo(userId); // 나머지는 그냥 DTO로 받자
        userInfoRepository.save(userInfo);
    }

    @Override
    public void updateUserInfo(UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo userInfo = userInfoRepository.findByUserId(userId);
        //userInfo.set~~ 바꾸고
        userInfoRepository.save(userInfo);
    }
}
