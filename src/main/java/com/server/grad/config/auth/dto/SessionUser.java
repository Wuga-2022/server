package com.server.grad.config.auth.dto;

import com.server.grad.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private Long id;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.id = user.getId();
    }
}
