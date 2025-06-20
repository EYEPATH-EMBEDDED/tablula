package com.project.capstone.controller.user;

import com.project.capstone.dto.user.UserUpdateRequestDto;
import com.project.capstone.service.user.UserUpdateServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.capstone.domain.user.User;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserUpdateController {

    private final UserUpdateServiceInterface userUpdateService;

    @PutMapping
    public ResponseEntity changeUser(@RequestBody UserUpdateRequestDto userUpdateDto) {
        //일단 검증하고나서, 이후 로직
        userUpdateService.updateUser(userUpdateDto);
        return ResponseEntity.ok("사용자 개인정보 변경이 완료되었습니다.");
    }

    @GetMapping
    public ResponseEntity getUser() {
        // 사용자 정보 조회 로직
        User userInfo = userUpdateService.getUser();
        return ResponseEntity.ok(userInfo); 
    }
}
