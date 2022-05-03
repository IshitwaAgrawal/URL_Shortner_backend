package com.ishitwa.url_shortner.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Document("UserUrl")
public class UserUrl {
    @Id
    private UUID uid;
    private UUID userId;
    private UUID urlId;

    public UserUrl() {
    }

    public UserUrl(UUID userId, UUID urlId) {
        this.uid = UUID.randomUUID();
        this.userId = userId;
        this.urlId = urlId;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUrlId() {
        return urlId;
    }

    public void setUrlId(UUID urlId) {
        this.urlId = urlId;
    }
}
