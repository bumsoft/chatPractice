package com.chat.chatPractice.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {

    @NotEmpty(message="이름을 입력하세요.")
    private String name;

    @NotEmpty(message="아이디를 입력하세요.")
    private String username;

    @NotEmpty(message="비밀번호를 입력하세요.")
    private String pw;
}
