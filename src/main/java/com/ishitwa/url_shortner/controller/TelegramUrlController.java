package com.ishitwa.url_shortner.controller;

import com.ishitwa.url_shortner.model.TelegramUrl;
import com.ishitwa.url_shortner.service.TelegramUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/telegram")
public class TelegramUrlController {

    @Autowired
    TelegramUrlService telegramUrlService;

    @PostMapping("/new")
    public ResponseEntity<?> createNewUrl(@RequestBody TelegramUrl url){
        try{
            telegramUrlService.createNewUrl(url);
            return new ResponseEntity<>(url,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }

    @GetMapping("/url/{url}")
    public ResponseEntity<Void> redirect(@PathVariable String url){
        try{
            URI dest_url = telegramUrlService.getLongUrl(url);
            return ResponseEntity.status(HttpStatus.FOUND).location(dest_url).build();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
