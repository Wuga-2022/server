package com.server.grad.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String email;
    private String role;
    private Long family_code;

    @Builder
    public UserUpdateRequestDto(String name, String email, String role, Long family_code){
        this.name = name;
        this.email = email;
        this.role = role;
        this.family_code = family_code;
    }
}
