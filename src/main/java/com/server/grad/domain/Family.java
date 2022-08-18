package com.server.grad.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int points = 0;

    // 아이템 목록 테이블 필요

    @Builder
    public Family(int points){
        this.points = points;
    }

    public void update(int points){
        this.points = points;
    }
}
