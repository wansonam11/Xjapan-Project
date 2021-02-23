package com.example.xjapan.service;

import com.example.xjapan.dto.ReplyDTO;
import com.example.xjapan.entity.Board;
import com.example.xjapan.entity.Reply;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    List<ReplyDTO> getList(Long bno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    //ReplyDTO -> Reply
    default Reply dtoToEntity(ReplyDTO replyDTO){

        Board board = Board.builder()
                .bno(replyDTO.getBno())
                .build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .text(replyDTO.getText())
                .replyer(replyDTO.getReplyer())
                .board(board)
                .build();

        return reply;
    };

    //Reply -> ReplyDTO
    default ReplyDTO entityToDTO(Reply reply){

        ReplyDTO dto = ReplyDTO.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .regDate(reply.getRegDate())
                .modDate(reply.getModDate())
                .build();

        return dto;
    };
}
