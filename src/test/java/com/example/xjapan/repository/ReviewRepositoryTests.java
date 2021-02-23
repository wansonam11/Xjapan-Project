package com.example.xjapan.repository;

import com.example.xjapan.entity.Member2;
import com.example.xjapan.entity.Photo;
import com.example.xjapan.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertPhotoReviews(){

        IntStream.rangeClosed(1, 200).forEach(i -> {

            Long pno = (long)(Math.random()*100) + 1;
            Long pid = (long)(Math.random()*100) + 1;

            Member2 member2 = Member2.builder()
                    .pid(pid)
                    .build();

            Review review = Review.builder()
                    .member2(member2)
                    .photo(Photo.builder()
                    .pno(pno)
                    .build())
                    .grade((int)(Math.random() * 5) + 1)
                    .text("이 사진은 정말 최고야~!!" + i)
                    .build();

            reviewRepository.save(review);
        });
    }

    @Test
    public void testGetPhotoReviews(){

        Photo photo = Photo.builder()
                .pno(62L)
                .build();

        List<Review> result = reviewRepository.findByPhoto(photo);

        result.forEach(review -> {
            System.out.println("\t" + review.getGrade());
            System.out.println("\t" + review.getText());
            System.out.println("\t" + review.getMember2().getEmail());
            System.out.println("-----------------------------");
        });

    }
}
