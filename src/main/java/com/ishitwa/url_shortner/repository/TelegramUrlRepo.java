package com.ishitwa.url_shortner.repository;

import com.ishitwa.url_shortner.model.TelegramUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.UUID;

@Repository
public interface TelegramUrlRepo extends JpaRepository<TelegramUrl, UUID> {

    TelegramUrl findTelegramUrlByShortUrl(String url);
}
