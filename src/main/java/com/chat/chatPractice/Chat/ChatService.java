package com.chat.chatPractice.Chat;

import com.chat.chatPractice.Chat.Entity.Chat;
import com.chat.chatPractice.Chat.Entity.ChatRoom;
import com.chat.chatPractice.Chat.repository.ChatRepository;

import com.chat.chatPractice.Chat.repository.ChatRoomRepository;
import com.chat.chatPractice.User.dto.RoomDto;
import com.chat.chatPractice.User.entity.User;
import com.chat.chatPractice.User.repository.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Data
@Slf4j
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public void sendChatToDB(ChatDTO chatDTO, ChatRoom chatRoom)
    {
        Chat chat = Chat.builder()
                .chatRoom(chatRoom)
                .senderId(chatDTO.getSenderId())
                .content(chatDTO.get_content())
                .build();

        chatRepository.save(chat);
    }

    public List<PrevChatDto> getPrevChat(ChatRoom chatRoom)
    {
        List<Chat> chats = chatRoom.getChats();

        if(chats==null) return new ArrayList<>();

        List<PrevChatDto> prevChatDtos = new ArrayList<>();

        for(Chat chat : chats)
        {
            String senderId = chat.getSenderId();
            Optional<User> _sender = userRepository.findByusername(senderId);
            if(_sender.isPresent())
            {
                String senderName = _sender.get().getName();

                PrevChatDto prevChatDto = new PrevChatDto(senderName, chat.getContent(),chat.getTime());
                prevChatDtos.add(prevChatDto);
            }
            else
            {
                String senderName = "탈퇴한 유저";

                PrevChatDto prevChatDto = new PrevChatDto(senderName, chat.getContent(),chat.getTime());
                prevChatDtos.add(prevChatDto);
            }
        }
        return prevChatDtos;

    }
//temp;
    public ChatRoom makeChatRoom(String senderId, String receiverId)
    {
        String roomId;
        if(senderId.compareTo(receiverId) > 0)
        {
            roomId = senderId + "+" + receiverId;
        }
        else
        {
            roomId = receiverId +"+"+ senderId;
        }

        //이미 있다면 찾아서 반환
        if(chatRoomRepository.findByChatRoomId(roomId).isPresent())
        {
            return chatRoomRepository.findByChatRoomId(roomId).get();
        }

        else
        {
            ChatRoom chatRoom =  ChatRoom.builder()
                    .chatRoomId(roomId)
                    .user1(senderId)
                    .user2(receiverId)
                    .build();

            return chatRoomRepository.save(chatRoom);

        }
    }

    public List<RoomDto> findChatRooms(String username)
    {
        List<ChatRoom> chatRooms = this.findChatRoomsByUsername(username);
        List<RoomDto> receivers = new ArrayList<>();

        for(ChatRoom chatRoom : chatRooms)
        {
            Optional<Chat> _chat = chatRepository.findFirstByChatRoomOrderByIdDesc(chatRoom);
            String content = "";
            if(_chat.isPresent())
            {
                content = _chat.get().getContent();
            }

            //user1이 본인이면 user2로 추가
            if(chatRoom.getUser1().equals(username))
            {
                if(makeRoomDto(chatRoom.getUser2(),content) != null)
                {
                    receivers.add(makeRoomDto(chatRoom.getUser2(),content));
                }
            }
            //user2가 본인인경우는.
            else
            {
                if(makeRoomDto(chatRoom.getUser1(),content) != null)
                {
                    receivers.add(makeRoomDto(chatRoom.getUser1(),content));
                }
            }
        }
        return receivers;
    }

    private RoomDto makeRoomDto(String username, String content)
    {
        Optional<User> _user = userRepository.findByusername(username);
        if(_user.isPresent())
        {

            return new RoomDto(username, _user.get().getName(),content);
        }
        else
        {
            return null;
        }
    }



    private List<ChatRoom> findChatRoomsByUsername(String username)
    {
        return chatRoomRepository.findByUser1OrUser2(username,username);
    }

}
