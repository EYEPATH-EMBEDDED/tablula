package com.project.capstone.controller.userinfo;

import com.project.capstone.dto.userinfo.UserInfoRequestDto;
import com.project.capstone.dto.userinfo.UserInfoUpdateRequestDto;
import com.project.capstone.service.userinfo.UserInfoServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("users")
public class UserProfileController {

    private final UserInfoServiceInterface userInfoService;

    @PostMapping("/info")
    public ResponseEntity registerUserInfo(@RequestBody UserInfoRequestDto userInfoRequestDto) {
        userInfoService.saveUserInfo(userInfoRequestDto);
        return ResponseEntity.ok("사용자 프로필 등록 완료");
    }

    @PutMapping("/info")
    public ResponseEntity changeUserInfo(@RequestBody UserInfoUpdateRequestDto userInfoUpdateRequestDto) {
        userInfoService.updateUserInfo(userInfoUpdateRequestDto);
        return ResponseEntity.ok("사용자 프로필 수정 완료");
    }
}
