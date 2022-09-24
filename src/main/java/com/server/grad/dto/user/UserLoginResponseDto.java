package com.server.grad.dto.user;

import com.server.grad.domain.user.User;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private Long id;
    private String email;
    private String name;
    private String member;
    private String profile_img;

    public UserLoginResponseDto(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.member = entity.getMember();
        this.profile_img = entity.getProfile_img();
    }
}
