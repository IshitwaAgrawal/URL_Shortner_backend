package com.ishitwa.url_shortner.service;

import com.ishitwa.url_shortner.conig.SecurityConstants;
import com.ishitwa.url_shortner.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationMail {

    @Autowired
    MailService mailService;

    public void sendVerificationMail(User user){
        String subject = "Please verify your registration.";
        String senderName = "Url_shortner services ...";
        String userMail = user.getEmail();
        String mailContent = "<p>Dear, "+user.getFirst_name()+" "+user.getLast_name()+"</p>";
        String site = SecurityConstants.hostUrl;
        String verifyUrl = "/api/verify/"+user.getVerificationToken();
        mailContent += "<p>Please click the link below to verify the registration</p>";
        mailContent += "<a href=\""+site+verifyUrl+"\">VERIFY</a><br>";
        mailService.sendMail(userMail,subject,senderName,mailContent);
    }
}
