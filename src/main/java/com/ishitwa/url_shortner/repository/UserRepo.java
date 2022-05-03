package com.ishitwa.url_shortner.repository;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepo extends MongoRepository<User, UUID> {

    User findUserById(UUID id);
    User findUserByUsername(String username);
    User findUserByEmail(String username);
    User findUserByVerificationToken(String token);
}
