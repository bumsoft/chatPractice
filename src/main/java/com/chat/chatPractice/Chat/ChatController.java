package com.chat.chatPractice.Chat;

import com.chat.chatPractice.User.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final UserService userService;


//    @PostMapping
//    public ChatRoom createRoom(@RequestParam String name)
//    {
//        return chatService.createRoom(name);
//    }
//
//    @GetMapping
//    public List<ChatRoom> findAllRooms()
//    {
//        System.out.println("list");
//        return chatService.findAllRoom();
//    }

    @GetMapping("startChat/{receiverId}")
    public String startChat(@PathVariable String receiverId, Model model, Principal principal)
    {
        // receiverId가 존재하는지 한 번 걸러주기
        // ...
        model.addAttribute("receiverId",receiverId);

        model.addAttribute("prevChat",chatService.getPrevChat(chatService.makeChatRoomId(principal.getName(),receiverId)));

        // 이전 대화내용 추가하기
        // ...

        return "chat";
    }
}
