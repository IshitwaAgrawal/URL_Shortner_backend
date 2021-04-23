package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.Exceptions.EmailExists;
import com.ishitwa.url_shortner.Exceptions.FieldsNotHaveValue;
import com.ishitwa.url_shortner.Exceptions.PasswordLength;
import com.ishitwa.url_shortner.Exceptions.UserExists;
import com.ishitwa.url_shortner.conig.SecurityConstants;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user)throws Exception{
        if(user.getUsername()==null)throw new FieldsNotHaveValue("Username");

        if(user.getEmail()==null)throw new FieldsNotHaveValue("Email");

        if(user.getPassword()==null)throw new FieldsNotHaveValue("Password");

        if(user.getFirst_name()==null)throw new FieldsNotHaveValue("First Name");

        if(user.getLast_name()==null)throw new FieldsNotHaveValue("Last Name");

        if(userRepo.findUserByUsername(user.getUsername()) != null)throw new UserExists(user.getUsername());

        if(userRepo.findUserByEmail(user.getEmail()) != null)throw new EmailExists(user.getEmail());

        if(user.getPassword().length() < SecurityConstants.PASSWORD_LENGTH)throw new PasswordLength(user.getPassword().length());

        try{
            String emailPattern = "^([a-zA-Z0-9\\.-]+)@([a-zA-Z0-9-]+)\\.([a-zA-Z]{2,8})([a-zA-Z]{2,8})?$";
            Pattern pattern = Pattern.compile(emailPattern);
            String email = user.getEmail();
            Matcher m = pattern.matcher(email);
            if(!m.matches()){
                throw new PatternSyntaxException("Email address not valid.",emailPattern,-1);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        catch (Exception e){
            throw e;
        }
    }

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
