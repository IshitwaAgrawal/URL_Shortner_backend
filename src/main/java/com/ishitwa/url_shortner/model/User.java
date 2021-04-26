package com.ishitwa.url_shortner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    /**
     * here, id is working as a primary key.
     */
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
    private Date signup_date;
    private boolean isVerified;
    private String roles;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Url> urls_list;
    private long createdUrls;
    private String verificationToken;

    /**
     * There are only two roles : 1 for Normal User, 2 for Admin User.
     * if the user is normal user then the value of role will have 1;
     * if the user is admin then the role will have value 2.
     */

    public User(){
        this.id = UUID.randomUUID();
        this.signup_date = new Date();
        this.isVerified = false;
        this.createdUrls=0;
        roles="ROLE_USER";
        this.verificationToken = RandomString.make(20);
    }

    public User(String first_name, String last_name, String email, String username, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public void addUrl(Url url){
        this.urls_list.add(url);
    }
    public void removeUrl(Url url){
        this.urls_list.remove(url);
    }

    public long getCreatedUrls() {
        return createdUrls;
    }

    public void setCreatedUrls(long createdUrls) {
        this.createdUrls = createdUrls;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Url> getUrls_list() {
        return urls_list;
    }

    public void setUrls_list(List<Url> urls_list) {
        this.urls_list = urls_list;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }
}
