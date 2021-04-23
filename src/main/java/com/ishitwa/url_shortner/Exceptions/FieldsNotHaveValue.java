package com.ishitwa.url_shortner.Exceptions;

public class FieldsNotHaveValue extends Exception{
    public FieldsNotHaveValue(String msg){
        super("the "+msg+" field is necessary.");
    }
}
