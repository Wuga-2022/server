package com.server.grad.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column
    private LocalDate date;

    @Column
    @ColumnDefault("false")
    private Boolean complete;

    // answer join
    @JsonIgnoreProperties({"question_id"})
    @OneToMany(mappedBy = "question_id", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Answers> answers = new ArrayList<>();

    @Builder
    public Question(String question, LocalDate date, Boolean complete, List<Answers> answers){
        this.question = question;
        this.date = date;
        this.complete = complete;
        this.answers = answers;
    }

    public void updateCompletion(Boolean complete){
        this.complete = complete;
    }
}
