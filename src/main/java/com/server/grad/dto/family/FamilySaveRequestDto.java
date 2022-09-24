package com.server.grad.dto.family;


import com.server.grad.domain.family.Family;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FamilySaveRequestDto {
    private String familycode;

    @Builder
    public FamilySaveRequestDto(String familycode){
        this.familycode = familycode;
    }

    public Family toEntity(){
        Family family = Family.builder()
                .familycode(familycode)
                .build();

        return family;
    }
}
