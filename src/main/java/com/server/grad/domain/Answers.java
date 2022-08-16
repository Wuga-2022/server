package com.server.grad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 타입 어떻게 하지??
    @Column
    private int emoji;

    @Column
    private String answer;

    @Column
    private LocalDate date;

    // question 테이블 조인 필요
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question_id;

    @Builder
    public Answers(int emoji, String answer, LocalDate date, Question question_id){
        this.emoji = emoji;
        this.answer = answer;
        this.date = date;
        this.question_id = question_id;
    }
}
