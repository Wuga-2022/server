package com.server.grad.dto.user;

import com.server.grad.domain.user.User;
import lombok.Getter;

@Getter
public class UserNameResponseDto {
    private String name;
    private String member;
    private String profile_img;

    public UserNameResponseDto(User entity){
        this.name = entity.getName();
        this.member = entity.getMember();
        this.profile_img = entity.getProfile_img();
    }
}
