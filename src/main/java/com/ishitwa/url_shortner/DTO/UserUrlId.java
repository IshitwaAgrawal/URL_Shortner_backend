package com.ishitwa.url_shortner.DTO;

public class UserUrlId {
    private String user_id;
    private String url_id;
    public UserUrlId(){
    }

    public UserUrlId(String user_id, String url_id) {
        this.user_id = user_id;
        this.url_id = url_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUrl_id() {
        return url_id;
    }

    public void setUrl_id(String url_id) {
        this.url_id = url_id;
    }
}
