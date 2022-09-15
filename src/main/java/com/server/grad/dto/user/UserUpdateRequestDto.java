package com.server.grad.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String member;
    private String profile_img;

    @Builder
    public UserUpdateRequestDto(String name,String member, String profile_img){
        this.name = name;
        this.member = member;
        this.profile_img = profile_img;
    }
}
