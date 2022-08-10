package com.server.grad.web;

import com.server.grad.service.MissionService;
import com.server.grad.web.dto.MissionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value="Mission Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MissionApiController {

    private final MissionService missionService;

    @GetMapping("/mission/{id}")
    @ApiOperation(value = "미션 반환")
    public MissionResponseDto findById(@PathVariable Long id){
        return missionService.findById(id);
    }

}
