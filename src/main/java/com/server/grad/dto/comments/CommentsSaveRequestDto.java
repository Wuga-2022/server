package com.server.grad.dto.comments;

import com.server.grad.domain.comments.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String comment;
    private int emj_good = 0;
    private int emj_heart = 0;
    private int emj_smile = 0;

    @Builder
    public CommentsSaveRequestDto(String comment){
        this.comment = comment;
    }

    public Comments toEntity(){
        Comments comments = Comments.builder()
                .emj_good(emj_good)
                .emj_heart(emj_heart)
                .emj_smile(emj_smile)
                .comment(comment)
                .build();

        return comments;
    }
}
