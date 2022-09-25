package com.server.grad.domain.comments;

import com.server.grad.domain.mission.Mission;
import com.server.grad.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int emj_good = 0;

    @Column
    private int emj_heart = 0;

    @Column
    private int emj_smile = 0;

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
    public Comments(int emj_good, int emj_heart, int emj_smile, String comment, LocalDate date, Mission mission_id, User user_id){
        this.emj_good = emj_good;
        this.emj_heart = emj_heart;
        this.emj_smile = emj_smile;
        this.comment = comment;
        this.date = date;
        this.mission_id = mission_id;
        this.user_id = user_id;
    }

    public void update(String comment, LocalDate date){
        this.comment = comment;
        this.date = date;
    }

    public void updateEmoji(int emj_good, int emj_heart, int emj_smile){
        this.emj_good = emj_good;
        this.emj_heart = emj_heart;
        this.emj_smile = emj_smile;
    }
}
