package com.server.grad.dto.mission;

import com.server.grad.domain.Mission;
import com.server.grad.dto.comments.CommentsResponseDto;
import lombok.*;

import lombok.Getter;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionResponseDto {
    private Long id;
    private List<String> mission;
    private List<String> images;
    private LocalDate date;
    private int similarity;
    private Boolean success;
    private List<CommentsResponseDto> comments;

    public MissionResponseDto(Mission entity){
        this.id = entity.getId();
        this.mission = entity.getImages().stream().map(String::valueOf).collect(Collectors.toList());
        this.images = entity.getImages().stream().map(String::valueOf).collect(Collectors.toList());
        this.date = entity.getDate();
        this.similarity = entity.getSimilarity();
        this.success = entity.getSuccess();
        this.comments = entity.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }

}
