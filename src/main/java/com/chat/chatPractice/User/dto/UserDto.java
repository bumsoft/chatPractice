package com.chat.chatPractice.User.dto;

import lombok.Getter;

@Getter
public class UserDto {

    public UserDto(String username, String name)
    {
        this.username = username;
        this.name = name;
    }

    private String username;

    private String name;
}
