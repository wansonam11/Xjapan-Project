package com.example.xjapan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long reviewnum;

    private Long pno;  //Photo

    private Long pid;   //Member2

    private String name;

    private String email;

    private int grade;

    private String text;

    private LocalDateTime regDate, modDate;
}
