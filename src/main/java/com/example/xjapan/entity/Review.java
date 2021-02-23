package com.example.xjapan.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"photo", "member2"})
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    private int grade;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Photo photo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member2 member2;

    public void changGrade(int grade){
        this.grade = grade;
    }

    public void changeText(String text){
        this.text = text;
    }

}
