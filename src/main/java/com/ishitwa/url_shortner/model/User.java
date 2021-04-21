package com.ishitwa.url_shortner.model;

import javax.persistence.*;
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
    private Date signup_date;
    private boolean isVerified;
    private byte role;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Url> urls_list;

    /**
     * There are only two roles : 1 for Normal User, 2 for Admin User.
     * if the user is normal user then the value of role will have 1;
     * if the user is admin then the role will have value 2.
     */

    public User(){}

    public User(String first_name, String last_name, String email, String username, byte role) {
        this.id = UUID.randomUUID();
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.signup_date = new Date();
        this.isVerified = false;
        this.role = role;
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

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }
}
