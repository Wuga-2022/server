package com.server.grad.dto.comments;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsEmojiUpdateReqDto {
    private int emj_good;
    private int emj_heart;
    private int emj_smile;
    private String user_name;

    @Builder
    public CommentsEmojiUpdateReqDto(int emj_good, int emj_heart, int emj_smile, String user_name){
        this.emj_good = emj_good;
        this.emj_heart = emj_heart;
        this.emj_smile = emj_smile;
        this.user_name = user_name;
    }
}
