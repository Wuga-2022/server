package com.server.grad.web;

import com.server.grad.config.auth.LoginUser;
import com.server.grad.config.auth.dto.SessionUser;
import com.server.grad.dto.user.UserResponseDto;
import com.server.grad.dto.user.UserSaveRequestDto;
import com.server.grad.dto.user.UserUpdateRequestDto;
import com.server.grad.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(value="User Controller", tags = "")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @PutMapping("/user")
    public Long update(@LoginUser SessionUser user, @RequestBody UserUpdateRequestDto requestDto) {
        return userService.update(user.getEmail(), requestDto);
    }

    @GetMapping("/user")
    public UserResponseDto findById(@LoginUser SessionUser user) {
        return userService.findById(user.getId());
    }
}
