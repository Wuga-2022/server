package com.server.grad.dto.mission;

import com.server.grad.domain.Mission;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MissionSaveRequestDto {
    private String mission;
    private String fam_image;
    private int date;
    private int similarity;
    private Boolean success;

    @Builder
    MissionSaveRequestDto(String mission, String fam_image, int date, int similarity, Boolean success){
        this.mission = mission;
        this.fam_image = fam_image;
        this.date = date;
        this.similarity = similarity;
        this.success = success;
    }

    public Mission toEntity(){
        Mission mission1 = Mission.builder()
                .mission(mission)
                .date(date)
                .similarity(similarity)
                .success(success)
                .build();

        return mission1;
    }
}
