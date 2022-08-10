package com.server.grad.web.dto;

import com.server.grad.domain.Mission;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionResponseDto {
    private Long id;
    private LocalDate date;
//    private   mission_image;
//    private   fam_image;
    private int similarity;
    private Boolean success;

    public MissionResponseDto(Mission entity){
        this.id = entity.getId();
        this.date = entity.getDate();
        this.similarity = entity.getSimilarity();
        this.success = entity.getSuccess();
    }
}
