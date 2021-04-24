package com.ishitwa.url_shortner.controller;

import com.ishitwa.url_shortner.model.AuthenticationRequest;
import com.ishitwa.url_shortner.model.AuthenticationResponse;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.service.UserService;
import com.ishitwa.url_shortner.util.JwtUtil;
import com.ishitwa.url_shortner.util.UtilFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody User user){
        try {
            userService.registerUser(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody AuthenticationRequest authenticationRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(token),HttpStatus.OK);
    }

    @GetMapping("/getAllUrls/{id}")
    public ResponseEntity<?> getUserUrls(@PathVariable String id){
        try {
            UUID uuid = UtilFunctions.getUUID(id);
            User user = userService.getUser(uuid);
            return new ResponseEntity<>(user.getUrls_list(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        try{
            UUID uuid = UtilFunctions.getUUID(id);
            return userService.deleteUser(uuid);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<?> verifyUser(@PathVariable String token){
        try {
            User u = userService.findUserByVerificationToken(token);
            u.setVerified(true);
            userService.updateUser(u);
            return new ResponseEntity<>("Verified Successfully!",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
