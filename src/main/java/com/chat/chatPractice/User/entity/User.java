package com.chat.chatPractice.User.entity;

import com.chat.chatPractice.User.dto.UserRegisterDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class User {

    public User(UserRegisterDto userRegisterDto)
    {
        this.username = userRegisterDto.getUsername();
        this.password = userRegisterDto.getPw();
        this.name = userRegisterDto.getName();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends ;
}