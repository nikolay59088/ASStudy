package com.nz.study;

public class AccSettings {

    private String  name;
    private String text;

    public AccSettings(String name, String text){
        this.name = name;
        this.text = text;
    }

    public String getName(){
        return this.name;
    }

    public String getText(){
        return this.text;
    }

}
