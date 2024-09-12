package com.chat.chatPractice.Chat.repository;

import com.chat.chatPractice.Chat.Entity.Chat;
import com.chat.chatPractice.Chat.Entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    Optional<Chat> findFirstByChatRoomOrderByIdDesc(ChatRoom chatRoom);
}
