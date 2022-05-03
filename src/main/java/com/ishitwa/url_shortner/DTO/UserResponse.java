package com.ishitwa.url_shortner.DTO;

import com.ishitwa.url_shortner.model.Url;
import com.ishitwa.url_shortner.model.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public class UserResponse {
    private UUID id;
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
    private Date signup_date;
    private boolean isVerified;
    private String roles;
    private long createdUrls;
    private String verificationToken;
    private List<Url> urls_list;

    public UserResponse(User user,List<Url> urlList){
        this.id=user.getId();
        this.first_name=user.getFirst_name();
        this.last_name=user.getLast_name();
        this.email=user.getEmail();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.signup_date=user.getSignup_date();
        this.isVerified=user.isVerified();
        this.roles=user.getRoles();
        this.createdUrls=user.getCreatedUrls();
        this.verificationToken=user.getVerificationToken();
        this.urls_list=urlList;
    }

    public UserResponse() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignup_date() {
        return signup_date;
    }

    public void setSignup_date(Date signup_date) {
        this.signup_date = signup_date;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public long getCreatedUrls() {
        return createdUrls;
    }

    public void setCreatedUrls(long createdUrls) {
        this.createdUrls = createdUrls;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public List<Url> getUrls_list() {
        return urls_list;
    }

    public void setUrls_list(List<Url> urls_list) {
        this.urls_list = urls_list;
    }
}
