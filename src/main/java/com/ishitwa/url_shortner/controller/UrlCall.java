package com.ishitwa.url_shortner.controller;

import com.ishitwa.url_shortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UrlCall {

    @Autowired
    private UrlService urlService;

    @GetMapping("/url/{url}")
    public ResponseEntity<Void> redirect(@PathVariable String url){
        try {
            URI dest_url = urlService.getLongUrl(url);
            return ResponseEntity.status(HttpStatus.FOUND).location(dest_url).build();
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
