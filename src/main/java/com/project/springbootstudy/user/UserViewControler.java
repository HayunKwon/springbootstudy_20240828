package com.project.springbootstudy.user;


import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewControler {

    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public String login(){
        return "/user/login";
    }

    @PostMapping("/logining")
    public String loginCheck(@RequestParam(name="userId") String userId
            , @RequestParam(name="password") String password
            , Model model) {

        boolean errorResult = userService.login(userId, password); //false이면 잘못된 것

        if(!errorResult) {
            model.addAttribute("error" , "없는 회원입니다. 회원가입부터 하셔야할듯");
            return "user/login";
        }

        return "redirect:/home";
    }


    @GetMapping("/user/joing")
    public String joing(){
        return "/user/joing";
    }

    @PostMapping("/joining")
    public String joining(@ModelAttribute User user, Model model ){
        // 사용자한테 회원가입이 되었다고 보여줘야되니깐?
        boolean isJoinSuccess=true; // 여기는 일단 회원가입 폼이 되는지 확인
        // 이제 조건들을 추가할 예정

        if(isJoinSuccess) {
            System.out.println("회원가입성공");
            return "redirect:/user/login";
        } else {
            model.addAttribute("error" , "회원가입에 실패하셨습니다. ㅠㅠㅠ 아쉽다");
            return "/user/joing";
        }

    }



}
