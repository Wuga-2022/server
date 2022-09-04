package com.server.grad.dto;

import com.server.grad.domain.Mission;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MissionResponseDto {
    private Long id;
    private LocalDate date;

    //타입 이미지로 수정
    private String mission_image;

    //타입 이미지로 수정
    private   String fam_image;

    private int similarity;
    private Boolean success;
    private List<CommentsResponseDto> comments;

    public MissionResponseDto(Mission entity){
        this.id = entity.getId();
        this.date = entity.getDate();
        this.mission_image = entity.getMission();
        this.fam_image = entity.getFam_image();
        this.similarity = entity.getSimilarity();
        this.success = entity.getSuccess();
        this.comments = entity.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }
}
