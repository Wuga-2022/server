package com.server.grad.dto.mission;

import com.server.grad.domain.Mission;
import com.server.grad.domain.Missions;
import com.server.grad.dto.comments.CommentsResponseDto;
import com.server.grad.service.S3Service;
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
    private String mission;
    private String image;
    private LocalDate date;
    private int similarity;
    private Boolean success;
    private List<CommentsResponseDto> comments;

    public MissionResponseDto(Mission entity){
        this.id = entity.getId();
        this.mission = "https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + entity.getMissions().getFilePath();
        this.image = "https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + entity.getImages().getFilePath();
        this.date = entity.getDate();
        this.similarity = entity.getSimilarity();
        this.success = entity.getSuccess();
        this.comments = entity.getComments().stream().map(CommentsResponseDto::new).collect(Collectors.toList());
    }

}
