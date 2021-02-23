package com.example.xjapan.service;

import com.example.xjapan.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ReplyServiceTests {

    @Autowired
    private ReplyService service;

    @Test
    public void testGetList(){

        Long bno = 100L;

        List<ReplyDTO> replyDTOList = service.getList(bno);

        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }

}