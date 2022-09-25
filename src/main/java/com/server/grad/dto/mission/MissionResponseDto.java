package com.server.grad.dto.mission;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.server.grad.domain.mission.Mission;
import com.server.grad.service.S3Service;
import lombok.*;

import lombok.Getter;
import java.time.LocalDate;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissionResponseDto {
    private Long id;
    private String mission;
    private String image;
    private LocalDate date;
    private int similarity;
    private Boolean success;

    public MissionResponseDto(Mission entity) {
        this.id = entity.getId();
        this.mission = "https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + entity.getMissions().getFilePath();
        if (entity.getImages() == null) {
            this.image = "none";
        } else {
            this.image = "https://" + S3Service.CLOUD_FRONT_DOMAIN_NAME + "/" + entity.getImages().getFilePath();
        }
        this.date = entity.getDate();
        this.similarity = entity.getSimilarity();
        this.success = entity.getSuccess();
    }

}

