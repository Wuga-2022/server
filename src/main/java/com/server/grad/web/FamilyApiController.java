package com.server.grad.web;

import com.server.grad.dto.family.FamilySaveRequestDto;
import com.server.grad.service.FamilyService;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Family Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FamilyApiController {

    private final FamilyService familyService;

    @PostMapping("/family")
    public Long save(@RequestBody FamilySaveRequestDto requestDto){
        return familyService.save(requestDto);
    }

    @GetMapping("/familycode")
    public String getFamilyCode(){
        return familyService.createCode();
    }
}
