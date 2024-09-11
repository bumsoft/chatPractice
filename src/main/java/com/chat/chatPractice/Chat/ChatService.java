package com.chat.chatPractice.Chat;

import com.chat.chatPractice.Chat.Entity.Chat;
import com.chat.chatPractice.Chat.repository.ChatRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;

    public void sendChatToDB(ChatDTO chatDTO, String roomId)
    {
        Chat chat = Chat.builder()
                .chatRoomId(roomId)
                .senderId(chatDTO.getSenderId())
                .content(chatDTO.get_content())
                .build();

        chatRepository.save(chat);
    }

    public List<Chat> getPrevChat(String roomId)
    {
        return chatRepository.findByChatRoomId(roomId);
    }

    public String makeChatRoomId(String senderId, String receiverId)
    {
        if(senderId.compareTo(receiverId) > 0)
        {
            return senderId+ "+" +receiverId;
        }
        else
        {
            return receiverId+"+"+senderId;
        }
    }
}
