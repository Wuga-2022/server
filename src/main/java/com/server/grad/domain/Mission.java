package com.server.grad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
//    private ?? 미션 이미지

//    @Column
//    private ?? 가족 사진 이미지

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private int similarity;

    @Column
    @ColumnDefault("false")
    private Boolean success;

    @Builder
    public Mission(LocalDate date, int similarity, Boolean success){
        this.date = date;
        this.similarity = similarity;
        this.success = success;
    }
}
