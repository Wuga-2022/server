package com.server.grad.domain;

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

    // 타입 어떻게 하지??
    @Column
    private int emoji;

    @Column
    private String answer;

    @Column
    private LocalDate date;

    // question 테이블 조인 필요

    @Builder
    public Answers(int emoji, String answer, LocalDate date){
        this.emoji = emoji;
        this.answer = answer;
        this.date = date;
    }
}
