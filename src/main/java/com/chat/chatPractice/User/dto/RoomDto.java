package com.chat.chatPractice.User.dto;

import lombok.Getter;

@Getter
public class RoomDto {

    public RoomDto(String username, String name, String content)
    {
        this.username = username;
        this.name = name;
        this.content = content;
    }

    private String username;

    private String name;

    private String content;
}
