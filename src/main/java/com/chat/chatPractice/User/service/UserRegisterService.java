package com.chat.chatPractice.User.service;

import com.chat.chatPractice.User.dto.UserRegisterDto;
import com.chat.chatPractice.User.entity.User;

public interface UserRegisterService {

    User userRegister(UserRegisterDto employeeRegisterDto);


    boolean idCheck(String id);
}
