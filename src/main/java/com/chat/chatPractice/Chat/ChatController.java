package com.chat.chatPractice.Chat;

import com.chat.chatPractice.Chat.Entity.ChatRoom;
import com.chat.chatPractice.User.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
        if(userService.isExist(receiverId))
        {



            model.addAttribute("receiverId", receiverId);
            model.addAttribute("receiverName", userService.getName(receiverId));
            model.addAttribute("senderName", userService.getName(principal.getName()));


            //이전 내용 추가
            ChatRoom chatRoom = chatService.makeChatRoom(principal.getName(), receiverId);
            model.addAttribute("prevChat", chatService.getPrevChat(chatRoom));


            return "chat";
        }
        else
        {
            return "receiver_not_found";
        }
    }
}
