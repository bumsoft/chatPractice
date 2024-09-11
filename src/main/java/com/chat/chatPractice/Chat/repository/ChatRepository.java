package com.chat.chatPractice.Chat.repository;

import com.chat.chatPractice.Chat.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findByChatRoomId(String chatRoomId);
}
