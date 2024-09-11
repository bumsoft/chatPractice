package com.chat.chatPractice.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public void addFriend(String username, String friendname)
    {
        Optional<User> byusername = userRepository.findByusername(username);
        User user = byusername.get();

        Optional<User> _friend = userRepository.findByusername(friendname);
        if (_friend.isPresent())
        {
            Friend f = new Friend();
            f.setUser(user);
            f.setFriendname(friendname);
            friendRepository.save(f);
        }
    }
}
