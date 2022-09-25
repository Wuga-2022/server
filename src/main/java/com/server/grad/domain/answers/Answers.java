package com.server.grad.domain.answers;

import com.server.grad.domain.question.Question;
import com.server.grad.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Answers {

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
    private String answer;

    @Column
    private LocalDate date;

    // question join
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question_id;

    // user join
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @Builder
    public Answers(int emj_good, int emj_heart, int emj_smile, String answer, LocalDate date, Question question_id, User user_id){
        this.emj_good = emj_good;
        this.emj_heart = emj_heart;
        this.emj_smile = emj_smile;
        this.answer = answer;
        this.date = date;
        this.question_id = question_id;
        this.user_id = user_id;
    }

    public void update(String answer, LocalDate date){
        this.answer = answer;
        this.date = date;
    }

    public void updateEmoji(int emj_good, int emj_heart, int emj_smile){
        this.emj_good = emj_good;
        this.emj_heart = emj_heart;
        this.emj_smile = emj_smile;
    }
}
