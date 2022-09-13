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
    private int emoji;
    private String comment;
    private LocalDate date = LocalDate.now();
    private Mission mission_id;
    private User user_id;

    @Builder
    public CommentsSaveRequestDto(int emoji, String comment, Mission mission_id, User user_id){
        this.emoji = emoji;
        this.comment = comment;
        this.mission_id = mission_id;
        this.user_id = user_id;
    }

    public Comments toEntity(){
        Comments comments = Comments.builder()
                .emoji(emoji)
                .comment(comment)
                .date(date)
                .mission_id(mission_id)
                .user_id(user_id)
                .build();

        return comments;
    }
}
