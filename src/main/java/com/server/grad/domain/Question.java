package com.server.grad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column
    private LocalDate date;

    @Column
    @ColumnDefault("false")
    private Boolean complete;

    // 유저랑 패밀리 테이블 조인 필요

    // answer join
//    @OneToMany(mappedBy = "question_id", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private List<Answers> answers;

    @Builder
    public Question(String question, LocalDate date, Boolean complete){
        this.question = question;
        this.date = date;
        this.complete = complete;
        //this.answers = answers;
    }
}
