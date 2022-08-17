package com.server.grad.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Long family_code;

//    @Column
//    private ?? 프로필 사진

    @Builder
    public User(String name, String email, String role, Long family_code){
        this.name = name;
        this.email = email;
        this.role = role;
        this.family_code = family_code;
    }

    public void update(String name, String email, String role, Long family_code){
        this.name = name;
        this.email = email;
        this.role = role;
        this.family_code = family_code;
    }
}
