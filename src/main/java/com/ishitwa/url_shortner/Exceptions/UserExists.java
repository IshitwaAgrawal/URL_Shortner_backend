package com.ishitwa.url_shortner.Exceptions;

public class UserExists extends Exception{
    public UserExists(String s){
        super(s+" already exists");
    }
    public UserExists(){
        super("User already exists");
    }
}
