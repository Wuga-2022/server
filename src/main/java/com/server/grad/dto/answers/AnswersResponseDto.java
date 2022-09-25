package com.server.grad.dto.answers;

import com.server.grad.domain.answers.Answers;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AnswersResponseDto {
    private Long id;
    private int emj_good;
    private int emj_heart;
    private int emj_smile;
    private String answer;
    private LocalDate date;
    private String user_name;
    private String user_profile;

    public AnswersResponseDto(Answers entity){
        this.id = entity.getId();
        this.emj_good = entity.getEmj_good();
        this.emj_heart = entity.getEmj_heart();
        this.emj_smile = entity.getEmj_smile();
        this.answer = entity.getAnswer();
        this.date = entity.getDate();
        this.user_name = entity.getUser_id().getName();
        this.user_profile = entity.getUser_id().getProfile_img();
    }
}
