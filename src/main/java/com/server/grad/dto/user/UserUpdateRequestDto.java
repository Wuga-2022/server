package com.server.grad.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String member;

    @Builder
    public UserUpdateRequestDto(String name,String member){
        this.name = name;
        this.member = member;
    }
}
