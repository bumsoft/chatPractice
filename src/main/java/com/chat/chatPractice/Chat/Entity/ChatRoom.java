package com.chat.chatPractice.Chat.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String chatRoomId;

    @Column(nullable = false)
    private String user1;

    private String user2;

    @OneToMany(mappedBy = "chatRoom")
    private List<Chat> chats;
}
