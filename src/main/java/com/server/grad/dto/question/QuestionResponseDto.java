package com.server.grad.dto.question;

import com.server.grad.domain.question.Question;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QuestionResponseDto {
    private Long id;
    private String question;
    private LocalDate date;
    private Boolean complete;

    public QuestionResponseDto(Question entity){
        this.id = entity.getId();
        this.question = entity.getQuestion();
        this.date = entity.getDate();
        this.complete = entity.getComplete();
    }
}
