package com.ishitwa.url_shortner.Exceptions;

import com.ishitwa.url_shortner.config.SecurityConstants;

public class PasswordLength extends Exception{
    public PasswordLength(int length){
        super("The password must be of minimmum length "+ SecurityConstants.PASSWORD_LENGTH +", but found to be of length "+length);
    }
}
