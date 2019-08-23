package com.nz.study;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Datas extends Application {

    private static Datas singleton;
    private String login = "none";
    private String password = "none";

    // Возвращает экземпляр данного класса
    public static Datas getInstance(){
        return singleton;
    }

    @Override

    public final void onCreate() {

        super.onCreate();


        singleton = this;

    }

    public void setDatas(String login, String password){
        this.login = login;
        this.password = password;

    }

    public void loadDatas(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Datas.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        this.login = myPreferences.getString("Login", "unknown");
        this.password = myPreferences.getString("Password", "unknown");

    }

    public String getLogin(){
        return this.login;
    }

    public String getPassword(){
        return  this.password;
    }

    public void saveDatas(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(Datas.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putString("Login", this.login);
        myEditor.putString("Password", this.password);
        myEditor.commit();
    }
}
