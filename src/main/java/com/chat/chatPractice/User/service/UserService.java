package com.chat.chatPractice.User.service;

import com.chat.chatPractice.User.dto.UserDto;
import com.chat.chatPractice.User.entity.Friend;
import com.chat.chatPractice.User.entity.User;
import com.chat.chatPractice.User.repository.FriendRepository;
import com.chat.chatPractice.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FriendRepository friendRepository;

    public boolean isExist(String username)
    {
        return userRepository.existsByusername(username);
    }

    public String getName(String username)
    {
        return userRepository.findByusername(username).get().getName();
    }


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

    //친구 아이디 및 이름 반환
    public List<UserDto> getFriends(String username)
    {
        Optional<User> _user = userRepository.findByusername(username);

        if(_user.isPresent())
        {
            List<Friend> friendList = friendRepository.findByUser(_user.get());

            List<UserDto> friends = new ArrayList<>();
            for (Friend friend : friendList) {

                Optional<User> _fr = userRepository.findByusername(friend.getFriendname());
                if(_fr.isPresent())
                {
                    User fr = _fr.get();
                    UserDto userDto = new UserDto(fr.getUsername(),fr.getName());

                    friends.add(userDto);
                }
            }
            return friends;
        }
        else {
            return null;
        }
    }

}
