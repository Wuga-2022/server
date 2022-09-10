package com.server.grad.dto.mission;

import com.server.grad.domain.Image;
import com.server.grad.domain.Mission;
import com.server.grad.dto.CommentsResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@Setter
public class MissionResponseDto {
    private Long id;
    private String mission;
    private List<String> images;
    private int date;
    private int similarity;
    private Boolean success;
    private List<CommentsResponseDto> comments;

    /*
    public MissionResponseDto(Mission entity){
        this.id = entity.getId();
        this.mission = entity.getMission();
        //this.images = entity.getImages();
        this.date = entity.getDate();
        this.similarity = entity.getSimilarity();
        this.success = entity.getSuccess();
        this.comments = entity.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }
*/
}
