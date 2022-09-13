package com.server.grad.web;

import com.server.grad.dto.family.FamilySaveRequestDto;

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

    @GetMapping("/familycode")
    @ApiOperation(value = "가족 코드 생성", notes = "코드 생성한 유저의 가족 결정됨")
    public String getFamilyCode(@LoginUser SessionUser user){
        return familyService.createCode(user.getEmail());
    }

    @PostMapping("/familycode")
    @ApiOperation(value = "가족 코드 입력", notes = "코드 입력한 유저의 가족 결정됨")
    public Long putFamilyCode(@LoginUser SessionUser user, @RequestParam String familycode) {
        return familyService.updateUserFamCode(user.getEmail(), familycode);
    }

//    @GetMapping("/family/{id}")
//    public FamilyResponseDto findById(@PathVariable Long id) {
//        return familyService.findById(id);
//    }

//    @PostMapping("/family")
//    public Long save (@RequestBody FamilySaveRequestDto requestDto){
//        return familyService.save(requestDto);
//    }


}

