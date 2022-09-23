package com.server.grad.dto.answers;

import com.server.grad.domain.Answers;
import com.server.grad.domain.Question;
import com.server.grad.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
public class AnswersSaveRequestDto {
    private String emoji;
    private String answer;

    @Builder
    public AnswersSaveRequestDto(String emoji, String answer){
        this.emoji = emoji;
        this.answer = answer;
    }

    // Dto -> Entity
    public Answers toEntity(){
        Answers answers = Answers.builder()
                .emoji(emoji)
                .answer(answer)
                .build();

        return answers;
    }
}
