package com.server.grad.dto.comments;

import com.server.grad.domain.Comments;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentsResponseDto {
    private Long id;
    private int emoji;
    private String comment;
    private LocalDate date;

    public CommentsResponseDto(Comments entity){
        this.id = entity.getId();
        this.emoji = entity.getEmoji();
        this.comment = entity.getComment();
        this.date = entity.getDate();
    }
}
