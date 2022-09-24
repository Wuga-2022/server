package com.server.grad.dto.user;

import com.server.grad.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String name;
    private String email;
    private String member;
    private Long family_id;
    private String profile_img;

    //repository를 통해 조회한 entity를 dto로 변환
    public UserResponseDto(User entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.member = entity.getMember();
        this.family_id = entity.getFamily_id().getId();
        this.profile_img = entity.getProfile_img();
    }
}
