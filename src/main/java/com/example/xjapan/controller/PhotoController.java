package com.example.xjapan.controller;

import com.example.xjapan.dto.PageRequestDTO;
import com.example.xjapan.dto.PhotoDTO;
import com.example.xjapan.service.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/photo")
@Log4j2
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(PhotoDTO photoDTO, RedirectAttributes redirectAttributes){

        log.info("photoDTO: " + photoDTO);

        Long pno = photoService.register(photoDTO);

        redirectAttributes.addFlashAttribute("msg", pno);

        return "redirect:/photo/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("pageRequestDTO: " + pageRequestDTO);

        model.addAttribute("result", photoService.getList(pageRequestDTO));
    }

    @GetMapping({"/read", "/modify"})
    public void read(Long pno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("pno: " + pno);

        PhotoDTO photoDTO = photoService.getPhoto(pno);

        model.addAttribute("dto", photoDTO);
    }

}
