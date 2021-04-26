package com.ishitwa.url_shortner.controller;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.service.UrlService;
import com.ishitwa.url_shortner.util.UtilFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.UUID;

@RestController
@RequestMapping("/api1")
public class UrlController {
    @Autowired
    UrlService urlService;
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
}
