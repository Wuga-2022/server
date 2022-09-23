package com.server.grad.dto.answers;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswersEmojiUpdateReqDto {
    private String emoji;
    private String user_name;

    @Builder
    public AnswersEmojiUpdateReqDto(String emoji, String user_name){
        this.emoji = emoji;
        this.user_name = user_name;
    }
}
