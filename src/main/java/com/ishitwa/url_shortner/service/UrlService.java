package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    UrlRepo urlRepo;
    @Autowired
    UserService userService;
    public ResponseEntity<?> registerNewUrl(Url url, UUID user_id){
        try{
            User k = userService.getUser(user_id);
            url.setUser(k);
            k.addUrl(url);
            userService.updateUser(k);
            urlRepo.save(url);
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> getLongUrl(String shorturl){
        try{
            String url = urlRepo.getUrl(shorturl).getLong_url();
            return new ResponseEntity<String>(url,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>("www.github.com",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
