package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.repository.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UrlService {
    @Autowired
    private UrlRepo urlRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserUrlService userUrlService;
    public ResponseEntity<?> registerNewUrl(Url url, UUID user_id)throws Exception{
        try{
            String u = url.getLong_url().toString();
            if(!u.substring(0,8).equals("https://")){
                u = "https://"+u;
                url.setLong_url(u);
            }
            User k = userService.getUser(user_id);
//            url.setUser(k);
//            k.addUrl(url);
            k.setCreatedUrls(k.getCreatedUrls()+1);
            userService.updateUser(k);
            urlRepo.save(url);
            userUrlService.addNewEntry(user_id,url.getId());
            return new ResponseEntity<>(getUrlsList(user_id), HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
        }
    }

    public ResponseEntity<?> deleteUrl(UUID urlId){
        try{
            Url url = urlRepo.findUrlById(urlId);
            User user=userService.getUser(userUrlService.findUserFromUrl(urlId));
//            user.getUrls_list().remove(url);
            user.setCreatedUrls((user.getCreatedUrls()-1)>0?(user.getCreatedUrls()-1):0);
            userService.updateUser(user);
//            url.setUser(null);
            urlRepo.delete(url);
            userUrlService.deleteUrl(urlId);
            return new ResponseEntity<>(getUrlsList(user.getId()),HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void updateUrl(Url url){
        urlRepo.save(url);
    }


    public URI getLongUrl(String url)throws Exception{
        System.out.println("*** URLSERVICE *** " + url);
        Url k = urlRepo.findUrlByShortUrl(url);
        System.out.println("*** URLSERVICE *** "+k.getLong_url());
        return k.getLong_url();
    }

    public void increment(UUID id)throws Exception{
        Url k= urlRepo.findUrlById(id);
        k.setClicks(k.getClicks()+1);
        updateUrl(k);
    }

    public Url getUrlFromId(UUID id){
        try{
            return urlRepo.findUrlById(id);
        }catch (Exception e){
            return null;
        }
    }

    public List<Url> getUrlsList(UUID userId) {
        List<Url> urls=new ArrayList<>();
        List<UUID> uuid=userUrlService.findUrlsByUserId(userId);
        for(UUID id:uuid){
            urls.add(getUrlFromId(id));
        }
        return urls;
    }
}
