package com.example.xjapan.service;

import com.example.xjapan.dto.BoardDTO;
import com.example.xjapan.dto.PageRequestDTO;
import com.example.xjapan.dto.PageResultDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTests {

    @Autowired
    private BoardService boardService;

    @Test
    public void testRegister(){

        BoardDTO dto = BoardDTO.builder()
                .title("X-japan Story")
                .content("X-japan was made by Yoshiki.")
                .writerEmail("user55@gmail.com")
                .build();

        Long bno = boardService.register(dto);
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO<BoardDTO, Object[]> result = boardService.getList(pageRequestDTO);

        for(BoardDTO boardDTO : result.getDtoList()){
            System.out.println(boardDTO);
        }
    }

    @Test
    public void testGet(){

        Long bno = 101L;

        BoardDTO boardDTO = boardService.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void testRemove(){

        Long bno = 11L;

        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify(){

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(101L)
                .title("X-japan is great~!!")
                .content("As already you know that..")
                .build();

        boardService.modify(boardDTO);
    }
}