package com.server.grad.dto.user;

import com.server.grad.domain.family.Family;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateFamilyDto {
    private Family family_id;

    @Builder
    public  UserUpdateFamilyDto(Family family_id){
        this.family_id = family_id;
    }
}
