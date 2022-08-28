package com.server.grad.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private String mission;

    @Column
    private String fam_image;

    @Column
    private LocalDate date;

    @Column
    private int similarity;

    @Column
    @ColumnDefault("false")
    private Boolean success;

    @JsonIgnoreProperties({"mission_id"})
    @OneToMany(mappedBy = "mission_id", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comments> comments = new ArrayList<>();

    @Builder
    public Mission(String mission, String fam_image, LocalDate date, int similarity, Boolean success, List<Comments> comments){
        this.mission = mission;
        this.fam_image = fam_image;
        this.date = date;
        this.similarity = similarity;
        this.success = success;
        this.comments = comments;
    }
}
