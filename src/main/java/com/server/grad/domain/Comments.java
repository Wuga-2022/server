package com.server.grad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @Column
    private LocalDate date;

//    @Column
//    private 유저 이름 유저 테이블에서 가져오기

    @Builder
    public Comments(String comment, LocalDate date){
        this.comment = comment;
        this.date = date;
    }
}
