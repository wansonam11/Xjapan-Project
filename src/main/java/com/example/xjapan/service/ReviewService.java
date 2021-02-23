package com.example.xjapan.service;

import com.example.xjapan.dto.ReviewDTO;
import com.example.xjapan.entity.Member2;
import com.example.xjapan.entity.Photo;
import com.example.xjapan.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getList(Long pno);

    Long register(ReviewDTO reviewDTO);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO reviewDTO){

        Review photoReview = Review.builder()
                .reviewnum(reviewDTO.getReviewnum())
                .photo(Photo.builder()
                .pno(reviewDTO.getPno()).build())
                .member2(Member2.builder()
                        .pid(reviewDTO.getPid()).build())
                        .grade(reviewDTO.getGrade())
                        .text(reviewDTO.getText()).build();
        return photoReview;
    }

    default ReviewDTO entityToDTO(Review review){

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewnum(review.getReviewnum())
                .pno(review.getPhoto().getPno())
                .pid(review.getMember2().getPid())
                .name(review.getMember2().getName())
                .email(review.getMember2().getEmail())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        return reviewDTO;
    }
}
