package com.example.xjapan.controller;

import com.example.xjapan.dto.ReviewDTO;
import com.example.xjapan.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{pno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("pno") Long pno){

        List<ReviewDTO> reviewDTOList = reviewService.getList(pno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{pno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO){

        Long reviewnum = reviewService.register(reviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @PutMapping("/{pno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO reviewDTO){

        reviewService.modify(reviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{pno}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum){

        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }
}
