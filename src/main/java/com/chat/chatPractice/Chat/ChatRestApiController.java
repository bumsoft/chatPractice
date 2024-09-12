package com.chat.chatPractice.Chat;

import com.chat.chatPractice.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//model.addAttribute("receiverId", receiverId);
//model.addAttribute("receiverName", userService.getName(receiverId));
//model.addAttribute("senderName", userService.getName(principal.getName()));

@RestController
@RequiredArgsConstructor
public class ChatRestApiController {

    private final ChatService chatService;
    private final UserService userService;




}
