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
    private String emoji = "0,0,0";

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
    public Answers(String emoji, String answer, LocalDate date, Question question_id, User user_id){
        this.emoji = emoji;
        this.answer = answer;
        this.date = date;
        this.question_id = question_id;
        this.user_id = user_id;
    }

    public void update(String answer, LocalDate date){
        this.answer = answer;
        this.date = date;
    }

    public String updateEmoji(String emoji){
        return this.emoji = emoji;
    }
}
