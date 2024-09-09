package com.chat.chatPractice.User;

public interface UserRegisterService {

    User userRegister(UserRegisterDto employeeRegisterDto);


    boolean idCheck(String id);
}
