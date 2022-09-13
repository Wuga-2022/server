package com.server.grad.dto.answers;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AnswersUpdateRequestDto {
    private String answer;
    private LocalDate date = LocalDate.now();

    @Builder
    public AnswersUpdateRequestDto(String answer){
        this.answer = answer;
    }
}
