package com.example.xjapan.service;

import com.example.xjapan.dto.ReviewDTO;
import com.example.xjapan.entity.Photo;
import com.example.xjapan.entity.Review;
import com.example.xjapan.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;


    @Override
    public List<ReviewDTO> getList(Long pno) {

        Photo photo = Photo.builder()
                .pno(pno).build();

        List<Review> result = reviewRepository.findByPhoto(photo);

        return result.stream().map(review ->
                entityToDTO(review)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO reviewDTO) {

        Review review = dtoToEntity(reviewDTO);

        reviewRepository.save(review);

        return review.getReviewnum();
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {

        Optional<Review> result = reviewRepository.findById(reviewDTO.getReviewnum());

        if(result.isPresent()){
            Review review = result.get();
            review.changGrade(reviewDTO.getGrade());
            review.changeText(reviewDTO.getText());

            reviewRepository.save(review);
        }
    }

    @Override
    public void remove(Long reviewnum) {

        reviewRepository.deleteById(reviewnum);
    }
}
