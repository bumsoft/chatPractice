package com.chat.chatPractice.User.controller;

import com.chat.chatPractice.Chat.ChatService;
import com.chat.chatPractice.User.dto.RoomDto;
import com.chat.chatPractice.User.dto.UserDto;
import com.chat.chatPractice.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserRestApiController {

    private final UserService userService;
    private final ChatService chatService;

    @GetMapping("/friends/all")
    public List<UserDto> findAllFriends(Principal principal)
    {
        if(principal==null)
        {
            //
        }

        return userService.getFriends(principal.getName());
    }

    //대화상대 리스트 반환해줌. => 후에 /startChat/대화상대 입력해서 연결되도록.
    @GetMapping("/chatRooms/all")
    public List<RoomDto> findAllChatRooms(Principal principal)
    {
        if(principal == null)
        {
            //
        }

        return chatService.findChatRooms(principal.getName());
    }
}
