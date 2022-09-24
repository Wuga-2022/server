package com.server.grad.dto.comments;

import com.server.grad.domain.comments.Comments;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentsResponseDto {
    private Long id;
    private String emoji;
    private String comment;
    private LocalDate date;
    private String user_name;
    private String user_profile;

    public CommentsResponseDto(Comments entity){
        this.id = entity.getId();
        this.emoji = entity.getEmoji();
        this.comment = entity.getComment();
        this.date = entity.getDate();
        this.user_name = entity.getUser_id().getName();
        this.user_profile = entity.getUser_id().getProfile_img();
    }
}
