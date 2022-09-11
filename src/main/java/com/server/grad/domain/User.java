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

    @Column
    private String member;

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

//    @Column
//    private ?? 프로필 사진

    @Builder
    public User(String name, String email, String member, Role role, Family family_id){
        this.name = name;
        this.email = email;
        this.member = member;
        this.role = role;
        this.family_id = family_id;
    }

    public void update(String name, String member){
        this.name = name;
        this.member = member;
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
