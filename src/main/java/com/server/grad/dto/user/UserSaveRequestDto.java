package com.server.grad.dto.user;

import com.server.grad.domain.user.Role;
import com.server.grad.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String name;
    private String email;
    private String member;
    private Role role;
    private String password;
    private String profile_img;

    @Builder
    public UserSaveRequestDto(String name, String email, String member, Role role, String password, String profile_img){
        this.name = name;
        this.email = email;
        this.member = member;
        this.role = role;
        this.password = password;
        this.profile_img = profile_img;
    }

    //request dto로 받은 user 객체를 entity화 하여 저장
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .member(member)
                .role(role)
                .password(password)
                .profile_img(profile_img)
                .build();
    }
}
