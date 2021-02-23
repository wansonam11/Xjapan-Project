package com.example.xjapan.repository;

import com.example.xjapan.entity.Member2;
import com.example.xjapan.entity.Photo;
import com.example.xjapan.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member2"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByPhoto(Photo photo);


    @Modifying
    @Query("delete from Review pr where pr.member2 = :member2")
    void deleteByMember(Member2 member2);
}
