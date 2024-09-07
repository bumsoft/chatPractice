package com.chat.chatPractice.chat;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;


    @PostMapping
    public ChatRoom createRoom(@RequestParam String name)
    {
        System.out.println("createRoom");
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRooms()
    {
        System.out.println("list");
        return chatService.findAllRoom();
    }
}
