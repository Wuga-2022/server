package com.server.grad.dto.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsEmojiUpdateReqDto {
    private String emoji;
    private String user_name;

    @Builder
    public CommentsEmojiUpdateReqDto(String emoji, String user_name){
        this.emoji = emoji;
        this.user_name = user_name;
    }
}
