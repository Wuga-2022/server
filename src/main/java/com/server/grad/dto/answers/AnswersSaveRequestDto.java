package com.server.grad.dto.answers;

import com.server.grad.domain.answers.Answers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswersSaveRequestDto {
    private String answer;
    private int emj_good = 0;
    private int emj_heart = 0;
    private int emj_smile = 0;

    @Builder
    public AnswersSaveRequestDto(String answer){
        this.answer = answer;
    }

    // Dto -> Entity
    public Answers toEntity(){
        Answers answers = Answers.builder()
                .emj_good(emj_good)
                .emj_heart(emj_heart)
                .emj_smile(emj_smile)
                .answer(answer)
                .build();

        return answers;
    }
}
