package com.example.xjapan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
@Log4j2
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public void main(){

    }

    @GetMapping("/profile")
    public void profile(){

    }


}
