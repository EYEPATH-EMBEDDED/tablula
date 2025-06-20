package com.project.capstone.service.user;

import com.project.capstone.domain.user.User;
import com.project.capstone.dto.user.UserUpdateRequestDto;
import com.project.capstone.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.project.capstone.domain.user.User;

@Service
@RequiredArgsConstructor
public class UserUpdateService implements UserUpdateServiceInterface {

    private final UserRepository userRepository;

    @Override
    public void updateUser(UserUpdateRequestDto userUpdateRequestDto) {
        //userId 꺼내서 이제 전체 수정을 하면 될 것 같다
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //회원 정보 수정 로직
        User updateUser = userRepository.findByUserId(userId);
        updateUser.setName(userUpdateRequestDto.getName());
        updateUser.setAge(userUpdateRequestDto.getAge());
        updateUser.setEmail(userUpdateRequestDto.getEmail());
        updateUser.setPhoneNum(userUpdateRequestDto.getPhoneNum());
        updateUser.setUserHeight(userUpdateRequestDto.getUserHeight());

        userRepository.save(updateUser);
    }

    @Override
    public User getUser() {
        //userId 꺼내서 전체 조회를 하면 될 것 같다
        String userId = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //회원 정보 조회 로직
        return userRepository.findByUserId(userId);
    }
}
