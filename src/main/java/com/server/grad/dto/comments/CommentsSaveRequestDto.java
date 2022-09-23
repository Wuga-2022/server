package com.server.grad.dto.comments;

import com.server.grad.domain.Comments;
import com.server.grad.domain.Mission;
import com.server.grad.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CommentsSaveRequestDto {
    private String emoji;
    private String comment;

    @Builder
    public CommentsSaveRequestDto(String emoji, String comment){
        this.emoji = emoji;
        this.comment = comment;
    }

    public Comments toEntity(){
        Comments comments = Comments.builder()
                .emoji(emoji)
                .comment(comment)
                .build();

        return comments;
    }
}
