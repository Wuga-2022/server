package com.server.grad.dto.user;

import com.server.grad.domain.Family;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String name;
    private String email;
    private String role;

    @Builder
    public UserUpdateRequestDto(String name, String email, String role){
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
