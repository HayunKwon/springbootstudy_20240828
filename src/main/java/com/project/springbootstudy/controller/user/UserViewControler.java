package com.project.springbootstudy.controller.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewControler {

    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }



}
