package com.chat.chatPractice.chat;

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
    private String chatRoomId;
    private String senderId;
    private String _content;

}
