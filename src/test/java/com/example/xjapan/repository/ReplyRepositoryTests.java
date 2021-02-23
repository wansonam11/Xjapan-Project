package com.example.xjapan.repository;

import com.example.xjapan.entity.Board;
import com.example.xjapan.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class ReplyRepositoryTests {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply(){

        IntStream.rangeClosed(1, 200).forEach(i ->{

            long bno = (long)(Math.random() * 100) + 1;

            Board board = Board.builder()
                    .bno(bno)
                    .build();

            Reply reply = Reply.builder()
                    .text("x-japan is great~~~!!" + i)
                    .board(board)
                    .replyer("x-fan")
                    .build();

            replyRepository.save(reply);

        });
    }

    @Test
    public void readReply1(){

        Optional<Reply> result = replyRepository.findById(1L);

        Reply reply = result.get();

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Test
    public void testListByBoard(){

        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(Board.builder()
                .bno(97L).build());

        replyList.forEach(reply -> {
            System.out.println(reply);
        });
    }
}