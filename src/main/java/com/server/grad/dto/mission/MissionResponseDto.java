package com.server.grad.dto.mission;

import com.server.grad.domain.Mission;
import com.server.grad.dto.CommentsResponseDto;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MissionResponseDto {
    private Long id;
    private LocalDate date;
    private String mission_image;
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
