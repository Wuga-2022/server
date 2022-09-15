package com.server.grad.dto.answers;

import com.server.grad.domain.Answers;
import com.server.grad.domain.Question;
import com.server.grad.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AnswersResponseDto {
    private Long id;
    private String emoji;
    private String answer;
    private LocalDate date;

    public AnswersResponseDto(Answers entity){
        this.id = entity.getId();
        this.emoji = entity.getEmoji();
        this.answer = entity.getAnswer();
        this.date = entity.getDate();
    }
}
