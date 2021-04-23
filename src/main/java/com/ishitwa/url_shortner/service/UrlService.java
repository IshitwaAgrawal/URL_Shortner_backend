package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.model.UserUrls;
import com.ishitwa.url_shortner.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    UrlRepo urlRepo;
    @Autowired
    UserService userService;
    public ResponseEntity<?> registerNewUrl(Url url, UUID user_id){
        try{
            String u = url.getLong_url().toString();
            if(!u.substring(0,8).equals("https://")){
                u = "https://"+u;
                url.setLong_url(u);
            }
            User k = userService.getUser(user_id);
            url.setUser(k);
            k.addUrl(url);
            k.setCreatedUrls(k.getCreatedUrls()+1);
            userService.updateUser(k);
            urlRepo.save(url);
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteUrl(UUID urlId){
        try{
            Url url = urlRepo.findUrlById(urlId);
            urlRepo.delete(url);
            return new ResponseEntity<>("URL Successfully Deleted",HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
        }
    }

    public ResponseEntity<?> getAllUrls(UUID uuid)throws Exception{
        try{
            User u = userService.getUser(uuid);
            UserUrls userUrls = new UserUrls();
            userUrls.setUserUrls(u.getUrls_list());
            return new ResponseEntity<>(userUrls,HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void updateUrl(Url url){
        urlRepo.save(url);
    }

    public URI getLongUrl(String url)throws Exception{
        Url k = urlRepo.findUrlByShort_url(url);
        k.setClicks(k.getClicks()+1);
        updateUrl(k);
        return k.getLong_url();
    }
}
