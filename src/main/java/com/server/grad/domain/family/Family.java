package com.server.grad.domain.family;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.grad.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "family_id")
    private Long id;

    @Column(nullable = false)
    private String familycode;


    // 1 : N user join
    @JsonIgnoreProperties({"family_id"})
    @OneToMany(mappedBy = "family_id", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<>();

    @Builder
    public Family(String familycode){
        this.familycode = familycode;
    }
}
