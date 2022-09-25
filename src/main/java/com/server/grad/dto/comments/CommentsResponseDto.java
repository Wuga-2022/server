package com.server.grad.dto.comments;

import com.server.grad.domain.comments.Comments;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentsResponseDto {
    private Long id;
    private int emj_good;
    private int emj_heart;
    private int emj_smile;
    private String comment;
    private LocalDate date;
    private String user_name;
    private String user_profile;

    public CommentsResponseDto(Comments entity){
        this.id = entity.getId();
        this.emj_good = entity.getEmj_good();
        this.emj_heart = entity.getEmj_heart();
        this.emj_smile = entity.getEmj_smile();
        this.comment = entity.getComment();
        this.date = entity.getDate();
        this.user_name = entity.getUser_id().getName();
        this.user_profile = entity.getUser_id().getProfile_img();
    }
}
