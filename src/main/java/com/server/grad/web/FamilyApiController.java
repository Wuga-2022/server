package com.server.grad.web;

import com.server.grad.dto.FamilyResponseDto;
import com.server.grad.dto.FamilyUpdateRequestDto;
import com.server.grad.service.FamilyService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Api(value="Family Controller", tags = "")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FamilyApiController {

    private final FamilyService familyService;

    @PutMapping("/family/{id}")
    public Long update(@PathVariable Long id, @RequestBody FamilyUpdateRequestDto requestDto) {
        return familyService.update(id, requestDto);
    }

    @GetMapping("/family/{id}")
    public FamilyResponseDto findById(@PathVariable Long id) {
        return familyService.findById(id);
    }
}
