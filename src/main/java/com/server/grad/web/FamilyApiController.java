package com.server.grad.web;

import com.server.grad.config.auth.LoginUser;
import com.server.grad.config.auth.dto.SessionUser;

import com.server.grad.service.FamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value="Family Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FamilyApiController {

    private final FamilyService familyService;

//    @GetMapping("/familycode/{")
//    @ApiOperation(value = "가족 코드 생성", notes = "코드 생성한 유저의 가족 결정됨")
//    public String getFamilyCode(@LoginUser SessionUser user){
//        return familyService.createCode(user.getEmail());
//    }
//
//    @PostMapping("/familycode")
//    @ApiOperation(value = "가족 코드 입력", notes = "코드 입력한 유저의 가족 결정됨")
//    public Long putFamilyCode(@LoginUser SessionUser user, @RequestParam String familycode) {
//        return familyService.updateUserFamCode(user.getEmail(), familycode);
//    }

    @GetMapping("/familycode/{u_id}")
    @ApiOperation(value = "가족 코드 생성", notes = "코드 생성한 유저의 가족 결정됨")
    public String getFamilyCode(@PathVariable Long u_id){
        return familyService.createCode(u_id);
    }

    @PostMapping("/familycode/{u_id}")
    @ApiOperation(value = "가족 코드 입력", notes = "코드 입력한 유저의 가족 결정됨")
    public Long putFamilyCode(@PathVariable Long u_id, @RequestParam String familycode) {
        return familyService.updateUserFamCode(u_id, familycode);
    }

}

