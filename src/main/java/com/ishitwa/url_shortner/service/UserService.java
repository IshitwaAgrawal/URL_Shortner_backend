package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.Exceptions.EmailExists;
import com.ishitwa.url_shortner.Exceptions.FieldsNotHaveValue;
import com.ishitwa.url_shortner.Exceptions.PasswordLength;
import com.ishitwa.url_shortner.Exceptions.UserExists;
import com.ishitwa.url_shortner.config.SecurityConstants;
import com.ishitwa.url_shortner.model.User;
import com.ishitwa.url_shortner.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private VerificationMail verificationMail;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user)throws Exception{
        if(user.getUsername()==null || user.getUsername()=="")throw new FieldsNotHaveValue("Username");

        if(user.getEmail()==null || user.getEmail()=="")throw new FieldsNotHaveValue("Email");

        if(user.getPassword()==null || user.getPassword()=="")throw new FieldsNotHaveValue("Password");

        if(user.getFirst_name()==null || user.getFirst_name()=="")throw new FieldsNotHaveValue("First Name");

        if(user.getLast_name()==null || user.getLast_name()=="")throw new FieldsNotHaveValue("Last Name");

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
//            verificationMail.sendVerificationMail(user);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        catch (Exception e){
            throw e;
        }
    }

    public User getUser(UUID id){
        return userRepo.findUserById(id);
    }
    public User getUser(String username){return userRepo.findUserByUsername(username);}

    public void updateUser(User k){
        userRepo.save(k);
    }

    public ResponseEntity<?> deleteUser(UUID uuid){
        try{
            User user = userRepo.findUserById(uuid);
            userRepo.delete(user);
            return new ResponseEntity<>("User successfully deleted!",HttpStatus.OK);
        }
        catch (Exception e){
            throw e;
        }
    }

    public User findUserByVerificationToken(String token){
        try{
            User u = userRepo.findUserByVerificationToken(token);
            return u;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepo.findUserByUsername(username);
        com.ishitwa.url_shortner.model.UserDetails userDetails = new com.ishitwa.url_shortner.model.UserDetails(u);
        return userDetails;
    }

    public int getUserCount(){
        List<User> users=userRepo.findAll();
        return users.size();
    }
}
