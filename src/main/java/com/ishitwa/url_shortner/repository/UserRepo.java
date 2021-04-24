package com.ishitwa.url_shortner.repository;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

    public User findUserById(UUID id);
    public User findUserByUsername(String username);
    public User findUserByEmail(String username);
    public User findUserByVerificationToken(String token);
}
