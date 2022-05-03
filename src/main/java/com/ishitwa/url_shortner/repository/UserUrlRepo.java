package com.ishitwa.url_shortner.repository;

import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.model.UserUrl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserUrlRepo extends MongoRepository<UserUrl, UUID> {

    List<UserUrl> findUserUrlByUserId(UUID userId);

    UserUrl findUserUrlByUrlId(UUID urlId);
}
