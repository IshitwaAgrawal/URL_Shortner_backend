package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<?> registerUser(User user){
        if(userRepo.save(user)!=null)
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("fail",HttpStatus.NOT_FOUND);
    }
}
