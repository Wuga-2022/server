package com.server.grad.dto.family;

import com.server.grad.domain.Family;
import lombok.Getter;

@Getter
public class FamilyResponseDto {
    private String familycode;

    public FamilyResponseDto(Family entity){
        this.familycode = entity.getFamilycode();
    }
}
