package com.server.grad.dto;

import com.server.grad.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String name;
    private String email;
    private String role;
    private Long family_code;

    //repository를 통해 조회한 entity를 dto로 변환
    public UserResponseDto(User entity){
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.role = entity.getRole();
        this.family_code = entity.getFamily_code();
    }
}
