package com.chat.chatPractice.Chat;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {

    public enum MessageType{
        ENTER, TALK
    }

    private MessageType messageType;

    private String senderId;

    private String receiverId;

    private String _content;

}
