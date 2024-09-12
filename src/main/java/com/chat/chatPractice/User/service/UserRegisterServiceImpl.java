package com.chat.chatPractice.User.service;

import com.chat.chatPractice.User.dto.UserRegisterDto;
import com.chat.chatPractice.User.entity.User;
import com.chat.chatPractice.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User userRegister(UserRegisterDto userRegisterDto)
    {
        //중복회원 등 확인하는 부분 추가


        String encodePW = passwordEncoder.encode(userRegisterDto.getPw());
        userRegisterDto.setPw(encodePW);
        User user = new User(userRegisterDto);
        return userRepository.save(user);
    }

    //중복아이디확인 메서드
    @Override
    public boolean idCheck(String id)
    {
        return userRepository.existsByusername(id);
    }
}
