package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.model.TelegramUrl;
import com.ishitwa.url_shortner.repository.TelegramUrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class TelegramUrlService {

    @Autowired
    private TelegramUrlRepo telegramUrlRepo;

    public ResponseEntity<?> createNewUrl(TelegramUrl url){
        try {
            telegramUrlRepo.save(url);
            return new ResponseEntity<>(url,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    public void update(TelegramUrl url){
        telegramUrlRepo.save(url);
    }

    public URI getLongUrl(String url)throws Exception{
        TelegramUrl telegramUrl = telegramUrlRepo.findTelegramUrlByShortUrl(url);
        telegramUrl.setClicks(telegramUrl.getClicks()+1);
        update(telegramUrl);
        return telegramUrl.getLong_url();
    }
}
