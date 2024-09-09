package com.chat.chatPractice.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 공통 쿼리 정의
    // 중복 id 확인
    boolean existsByusername(String id);

    // User 회원탈퇴. 삭제
    void deleteById(Long user_id);

    //회원조회(로그인시)
    Optional<User> findByusername(String id);
}
