package com.project.springbootstudy.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/")
    public String goLogin(){
        return "/user/login";
    }
    @GetMapping("/home")
    public String  homepage(){
        return "/home";
    }
}
