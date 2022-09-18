package com.server.grad.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @OneToOne(mappedBy = "mission", cascade = CascadeType.ALL)
    @JsonBackReference
    private Missions missions;

    @OneToOne(mappedBy = "mission", cascade = CascadeType.ALL)
    @JsonBackReference
    private Images images;

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
    public Mission(LocalDate date, int similarity, Boolean success, List<Comments> comments){
        this.date = date;
        this.similarity = similarity;
        this.success = success;
        this.comments = comments;
    }

    public static Mission createMission(LocalDate date, int similarity, Boolean success, List<Comments> comments){
        Mission mission1 = new Mission();
        mission1.setDate(date);
        mission1.setSimilarity(similarity);
        mission1.setSuccess(success);
        mission1.setComments(comments);

        return mission1;
    }

    public void update(LocalDate date, int similarity, Boolean success, List<Comments> comments){
        this.date = date;
        this.similarity = similarity;
        this.success = success;
        this.comments = comments;
    }
}
