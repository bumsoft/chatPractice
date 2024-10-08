package com.chat.chatPractice.User.controller;

import com.chat.chatPractice.User.dto.UserRegisterDto;
import com.chat.chatPractice.User.service.UserRegisterService;
import com.chat.chatPractice.User.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRegisterService userRegisterService;

    @GetMapping("register")
    public String registerEmployer(Model model)
    {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "User/register";
    }


    @PostMapping("register")
    public String registerEmployer(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult)
    {
        //유효성 검사
        if (bindingResult.hasErrors())
        {
            return "User/register";
        }


        //저장로직 추가
        try {
            userRegisterService.userRegister(userRegisterDto);
        }catch(Exception e){
            e.printStackTrace();
            bindingResult.reject("error","기타 에러");
            return "User/register";
        }

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login()
    {
        return "User/loginForm";
    }


    @GetMapping("/myPage")
    public String myPage()
    {
        return "User/myPage";
    }

    @PostMapping("addFriend")
    public String addFriend(String username, Principal principal)
    {
        log.info(username);
        userService.addFriend(principal.getName(),username);

        return "redirect:/myPage";
    }

}
