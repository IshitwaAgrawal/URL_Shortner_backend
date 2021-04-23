package com.ishitwa.url_shortner.Exceptions;

public class EmailExists extends Exception{
    public EmailExists(String e){
        super("User with email "+e+" already exists.\n");
    }
    public EmailExists(){
        super("Email already exists");
    }
}
