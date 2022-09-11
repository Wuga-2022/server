package com.server.grad.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "family_id", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<User> users = new ArrayList<>();


    @Builder
    public Family(String familycode){
        this.familycode = familycode;
    }
}
