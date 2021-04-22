package com.ishitwa.url_shortner.controller;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigInteger;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api1")
public class UrlController {
    @Autowired
    UrlService urlService;
    @PostMapping("/createNewUrl/{id}")
    public ResponseEntity<?> registerUrl(@RequestBody Url url, @PathVariable String id){
        UUID uuid = new UUID(
                new BigInteger(id.substring(0, 16), 16).longValue(),
                new BigInteger(id.substring(16), 16).longValue());
        return urlService.registerNewUrl(url,uuid);
    }
    @GetMapping("/{url}")
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
