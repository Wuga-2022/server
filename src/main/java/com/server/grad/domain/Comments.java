package com.server.grad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private  int emoji;

    @Column
    private String comment;

    @Column
    private LocalDate date;

    //mission join
    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission_id;

    //user join
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @Builder
    public Comments(int emoji, String comment, LocalDate date, Mission mission_id, User user_id){
        this.emoji = emoji;
        this.comment = comment;
        this.date = date;
        this.mission_id = mission_id;
        this.user_id = user_id;
    }

    public void update(String comment, LocalDate date){
        this.comment = comment;
        this.date = date;
    }
}
