package com.chat.chatPractice.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
@Data
@Slf4j
public class ChatService {

    private final ObjectMapper mapper;
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init()
    {
        chatRooms = new LinkedHashMap<>();
    }
    public List<ChatRoom> findAllRoom()
    {
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId)
    {
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name)
    {
        //UUID로 roomId생성
        String roomId = UUID.randomUUID().toString();

        ChatRoom room = ChatRoom.builder()
                .roomId(roomId)
                .name(name)
                .build();

        chatRooms.put(roomId, room);
        return room;
    }

    public <T> void sendMessage(WebSocketSession session, T message)
    {
        try{
            session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
        }catch(IOException e){
            log.error(e.getMessage(),e);
        }
    }

}
