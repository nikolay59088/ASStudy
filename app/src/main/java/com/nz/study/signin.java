package com.nz.study;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class signin extends AppCompatActivity {

    EditText login, password;
    Button but;
    SharedPreferences.Editor myEditor;
    SharedPreferences myPreferences;
    private SQLzapros_fio zapros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.signin);


        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        but = findViewById(R.id.Accept);
        but.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Result();
            }
        });

    }


    private void Result(){
        if (login.getText().toString().length() > 0)
            if (password.getText().toString().length() > 0){
                if (db() == "success"){
                    Toast.makeText(this, "Вход выполнен", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signin.this, main.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                }

            }
            else
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show();
    }



    public String db(){

    zapros = new SQLzapros_fio();
    zapros.start(login.getText().toString(), password.getText().toString());

    try {
        zapros.join();
    }
    catch (InterruptedException ie){
        Toast.makeText(this, "Сервер не доступен. Попробуйте позже", Toast.LENGTH_SHORT).show();
    }

    if (zapros.resname() != "11"){
        Datas datas = Datas.getInstance();
        datas.onCreate();
        datas.setDatas(zapros.reslogin(),zapros.respassword());
        datas.saveDatas();


        return "success";
        }
    else
        return "fail";
    }


}
