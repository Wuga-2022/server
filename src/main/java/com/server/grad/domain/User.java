package com.server.grad.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String password;

    @Column
    private String member;

    @Column
    private String profile_img;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // N : 1 family join
    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family_id;

    // 1 : N answer join
    @JsonIgnoreProperties({"user_id"})
    @OneToMany(mappedBy = "user_id", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Answers> answers = new ArrayList<>();

    @Builder
    public User(String name, String email, String member, String password, String profile_img,Role role, Family family_id){
        this.name = name;
        this.email = email;
        this.member = member;
        this.password = password;
        this.profile_img = profile_img;
        this.role = role;
        this.family_id = family_id;
    }

    public void update(String name, String member, String profile_img){
        this.name = name;
        this.member = member;
        this.profile_img = profile_img;
    }

    public User updateSocial(String name){
        this.name = name;
        return this;
    }

    public void updateFamily(Family family_id){
        this.family_id = family_id;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
