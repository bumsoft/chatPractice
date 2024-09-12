package com.chat.chatPractice.User.repository;

import com.chat.chatPractice.User.entity.Friend;
import com.chat.chatPractice.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByUser(User user);
}
