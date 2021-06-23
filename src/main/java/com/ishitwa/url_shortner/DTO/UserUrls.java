package com.ishitwa.url_shortner.DTO;


import com.ishitwa.url_shortner.model.Url;

import java.util.List;

public class UserUrls {

    private List<Url> userUrls;

    public UserUrls(List<Url> u){
        this.userUrls = u;
    }

    public List<Url> getUserUrls() {
        return userUrls;
    }

    public void setUserUrls(List<Url> userUrls) {
        this.userUrls = userUrls;
    }

    @Override
    public String toString() {
        String s = "";
        for(Object u:userUrls.toArray()){
            s += u.toString();
        }
        return s;
    }
}
