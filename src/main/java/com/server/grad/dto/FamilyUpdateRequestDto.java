package com.server.grad.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FamilyUpdateRequestDto {

    private int points;

    @Builder
    public FamilyUpdateRequestDto(int points){
        this.points = points;
    }
}
