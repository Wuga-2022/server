package com.server.grad.web;

import com.server.grad.config.auth.LoginUser;
import com.server.grad.config.auth.dto.SessionUser;
import com.server.grad.dto.user.*;
import com.server.grad.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(value="User Controller", tags = "")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입", notes = "")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @GetMapping("/login")
    @ApiOperation(value = "로그인", notes = "이메일&비밀번호 맞으면 유저 아이디 반환. 틀리면 0(Long) 반환")
    public UserLoginResponseDto login(@RequestParam String email, @RequestParam String password){
        return userService.login(email, password);
    }

//    @PutMapping("/user")
//    @ApiOperation(value = "유저 정보 수정", notes = "")
//    public Long update(@LoginUser SessionUser user, @RequestBody UserUpdateRequestDto requestDto) {
//        return userService.update(user.getEmail(), requestDto);
//    }
//
//    @GetMapping("/user")
//    @ApiOperation(value = "유저 정보 반환", notes = "")
//    public UserResponseDto findById(@LoginUser SessionUser user) {
//        return userService.findById(user.getId());
//    }

    @PutMapping("/user/{u_id}")
    @ApiOperation(value = "유저 정보 수정", notes = "")
    public Long update(@PathVariable Long u_id, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(u_id, requestDto);
    }

    @GetMapping("/user/{u_id}")
    @ApiOperation(value = "유저 정보 반환", notes = "")
    public UserResponseDto findById(@PathVariable Long u_id) {
        return userService.findById(u_id);
    }
}
