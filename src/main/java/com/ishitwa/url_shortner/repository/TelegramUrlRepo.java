package com.ishitwa.url_shortner.repository;

import com.ishitwa.url_shortner.model.TelegramUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.UUID;


@Repository
public interface TelegramUrlRepo extends MongoRepository<TelegramUrl, UUID> {

    TelegramUrl findTelegramUrlByShortUrl(String url);

}
