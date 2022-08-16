package com.server.grad.dto;

import com.server.grad.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String role;
    private Long family_code;

    @Builder
    public UserSaveRequestDto(String name, String email, String role, Long family_code){
        this.name = name;
        this.email = email;
        this.role = role;
        this.family_code = family_code;
    }

    //request dto로 받은 user 객체를 entity화 하여 저장
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .role(role)
                .family_code(family_code)
                .build();
    }
}
