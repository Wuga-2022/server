package com.server.grad.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CommentsUpdateRequestDto {
    private String comment;
    private LocalDate date = LocalDate.now();

    @Builder
    public CommentsUpdateRequestDto(String comment){
        this.comment = comment;
    }
}
