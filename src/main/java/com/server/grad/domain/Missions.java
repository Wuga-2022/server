package com.server.grad.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity(name = "MissionImages")
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Missions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missionImage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Column(nullable = false, name = "file_path")
    private String filePath;
}
