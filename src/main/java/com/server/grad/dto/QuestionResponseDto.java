package com.server.grad.dto;

import com.server.grad.domain.Question;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QuestionResponseDto {
    private Long id;
    private String question;
    private LocalDate date;
    private Boolean complete;
    //private List<AnswersResponseDto> answers;

    public QuestionResponseDto(Question entity){
        this.id = entity.getId();
        this.question = entity.getQuestion();
        this.date = entity.getDate();
        this.complete = entity.getComplete();
        //this.answers = entity.getAnswers().stream().map(AnswersResponseDto::new).collect(Collectors.toList());
    }
}
