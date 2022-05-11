package com.ishitwa.url_shortner.controller;

import com.ishitwa.url_shortner.DTO.UserUrlId;
import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.DTO.UserUrls;
import com.ishitwa.url_shortner.service.UrlService;
import com.ishitwa.url_shortner.service.UserService;
import com.ishitwa.url_shortner.util.UtilFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthScrollBarUI;
import java.net.URI;
import java.util.UUID;

@Transactional
@RestController
@RequestMapping("/api1")
public class UrlController {


    @Autowired
    private UrlService urlService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @PostMapping("/createNewUrl/{id}")
    public ResponseEntity<?> registerUrl(@RequestBody Url url, @PathVariable String id)throws Exception{
        UUID uuid = UtilFunctions.getUUID(id);
        try{
            return new ResponseEntity<>(urlService.registerNewUrl(url,uuid),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NON_AUTHORITATIVE_INFORMATION);
        }
    }
    @GetMapping("/deleteUrl/{id}")
    public ResponseEntity<?> deleteUrl(@PathVariable String id){
        UUID uuid = UtilFunctions.getUUID(id);
        try{
            return urlService.deleteUrl(uuid);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @MessageMapping("/clicks")
    public void increment(@Payload UserUrlId id)throws Exception{
        Thread.sleep(600);
        UUID user_id = UtilFunctions.getUUID(id.getUser_id());
        UUID url_id = UtilFunctions.getUUID(id.getUrl_id());
        urlService.increment(url_id);
        messagingTemplate.convertAndSendToUser(id.getUser_id(),"/queue/clicks",new UserUrls(urlService.getUrlsList(user_id)));
    }

    @MessageMapping("/newurl")
    public void newUrlCreated(@Payload UserUrlId userid)throws Exception{
        Thread.sleep(600);
        UUID uid = UtilFunctions.getUUID(userid.getUser_id());
        messagingTemplate.convertAndSendToUser(userid.getUser_id(),"/queue/newurl",new UserUrls(urlService.getUrlsList(uid)));
    }

    @GetMapping("/url/{url}")
    public ResponseEntity<Void> redirect(@PathVariable String url){
        try {
            System.out.println("*** URLCONTROLLER *** "+url);
            URI dest_url = urlService.getLongUrl(url);
            System.out.println("*** URLCONTROLLER *** "+dest_url.toString());
            return ResponseEntity.status(HttpStatus.FOUND).location(dest_url).build();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
