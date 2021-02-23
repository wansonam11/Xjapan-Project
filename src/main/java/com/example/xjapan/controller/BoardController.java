package com.example.xjapan.controller;

import com.example.xjapan.dto.BoardDTO;
import com.example.xjapan.dto.PageRequestDTO;
import com.example.xjapan.security.dto.ClubAuthMemberDTO;
import com.example.xjapan.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model, @AuthenticationPrincipal ClubAuthMemberDTO clubAuthMember){

        model.addAttribute("result", boardService.getList(pageRequestDTO));

        log.info("User.......");
        log.info("====================");
        log.info(clubAuthMember);
    }

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(BoardDTO dto, RedirectAttributes redirectAttributes){

        Long bno = boardService.register(dto);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read", "/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                     Long bno, Model model){

        BoardDTO boardDTO = boardService.get(bno);

        model.addAttribute("dto", boardDTO);
    }

    @PostMapping("/modify")
    public String modify(@ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         BoardDTO dto, RedirectAttributes redirectAttributes){

        boardService.modify(dto);

        redirectAttributes.addAttribute("page", requestDTO.getPage());
        redirectAttributes.addAttribute("type", requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());

        redirectAttributes.addAttribute("bno", dto.getBno());

        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){

        boardService.removeWithReplies(bno);

        redirectAttributes.addFlashAttribute("msg", bno);

        return "redirect:/board/list";
    }
}
