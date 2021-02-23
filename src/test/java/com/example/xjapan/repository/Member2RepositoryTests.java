package com.example.xjapan.repository;

import com.example.xjapan.entity.Member2;
import com.example.xjapan.entity.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class Member2RepositoryTests {

    @Autowired
    private Member2Repository member2Repository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers2() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member2 member2 = Member2.builder()
                    .email("xjapan" + i + "@gmail.com")
                    .password("1111")
                    .name("x-fan" + i)
                    .build();

            member2Repository.save(member2);
        });
    }

    @Commit
    @Transactional
    @Test
    public void deleteMembers2(){

        Long pid =  1L;

        Member2 member2 = Member2.builder()
                .pid(pid)
                .build();

        reviewRepository.deleteByMember(member2);
        member2Repository.deleteById(pid);

    }
}
