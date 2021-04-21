package com.ishitwa.url_shortner.controller;

import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.registerUser(user);
    }
}
