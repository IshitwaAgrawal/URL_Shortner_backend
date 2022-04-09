package com.ishitwa.url_shortner.model;

import net.bytebuddy.utility.RandomString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

@Document("telegramUrl")
public class TelegramUrl {

    @Id
    private UUID id;
    private URI long_url;
    private String shortUrl;
    private long clicks;
    private long date;
    public TelegramUrl(){
        this.id = UUID.randomUUID();
        this.clicks=0;
        this.shortUrl = RandomString.make(6);
        this.date = new Date().getTime();
    }
    public TelegramUrl(String url){
        this.long_url = URI.create(url);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public URI getLong_url() {
        return long_url;
    }

    public void setLong_url(URI long_url) {
        this.long_url = long_url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public long getClicks() {
        return clicks;
    }

    public void setClicks(long clicks) {
        this.clicks = clicks;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
