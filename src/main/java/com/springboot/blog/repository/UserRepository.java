package com.springboot.blog.repository;

import com.springboot.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserNameOrEmail(String username , String email);

    Optional<User> findByUserName(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUserName(String username);
}
