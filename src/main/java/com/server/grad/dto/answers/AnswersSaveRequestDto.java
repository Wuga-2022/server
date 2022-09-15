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
    private LocalDate date = LocalDate.now();
    private Question question_id;
    private User user_id;

    @Builder
    public AnswersSaveRequestDto(String emoji, String answer, Question question_id, User user_id){
        this.emoji = emoji;
        this.answer = answer;
        this.question_id = question_id;
        this.user_id = user_id;
    }

    // Dto -> Entity
    public Answers toEntity(){
        Answers answers = Answers.builder()
                .emoji(emoji)
                .answer(answer)
                .date(date)
                .question_id(question_id)
                .user_id(user_id)
                .build();

        return answers;
    }
}
