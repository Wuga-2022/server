package com.server.grad.dto.user;

import com.server.grad.domain.Family;
import com.server.grad.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String name;
    private String email;
    private String member;
    private Family family_id;
    private String profile_img;

    //repository를 통해 조회한 entity를 dto로 변환
    public UserResponseDto(User entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.member = entity.getMember();
        this.family_id = entity.getFamily_id();
        this.profile_img = entity.getProfile_img();
    }
}
