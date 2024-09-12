package com.chat.chatPractice.Chat.repository;

import com.chat.chatPractice.Chat.Entity.Chat;
import com.chat.chatPractice.Chat.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    boolean existsByChatRoomId(String chatRoomId);

    Optional<ChatRoom> findByChatRoomId(String chatRoomId);

    List<ChatRoom> findByUser1OrUser2(String user1, String user2);

    //List<Chat> findChatsByChatRoom(ChatRoom chatRoom);
}
