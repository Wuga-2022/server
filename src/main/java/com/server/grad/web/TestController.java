package com.server.grad.web;

import com.server.grad.config.auth.LoginUser;
import com.server.grad.config.auth.dto.SessionUser;
import com.server.grad.domain.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Test Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class TestController {

    @GetMapping("/loginSuccess")
    public SessionUser getLoginUser(@LoginUser SessionUser user){
        return user;
    }
}
