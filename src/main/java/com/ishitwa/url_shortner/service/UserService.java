package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<?> registerUser(User user){
        if(userRepo.save(user)!=null)
            return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
        else {
            //TODO Add different exceptions
            //TODO Add User email verification service
            return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
        }
    }

    //TODO Add login service

    public User getUser(UUID id){
        return userRepo.getOne(id);
    }

    public void updateUser(User k){
        userRepo.save(k);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepo.findUserByUsername(username);
        com.ishitwa.url_shortner.model.UserDetails userDetails = new com.ishitwa.url_shortner.model.UserDetails(u);
        return userDetails;
    }
}
