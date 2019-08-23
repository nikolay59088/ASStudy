package com.nz.study;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class log_in extends AppCompatActivity {

    EditText login, password, name, secname, age, universe, group;
    Button reg;
    SQLiteDatabase myDB;
    SQLregistration zapros;
    Datas dat;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        login = (EditText) findViewById(R.id.reg_login);
        password = (EditText) findViewById(R.id.reg_password);
        name = (EditText) findViewById(R.id.reg_name);
        secname = (EditText) findViewById(R.id.reg_secname);
        age = (EditText) findViewById(R.id.reg_age);
        universe = (EditText) findViewById(R.id.reg_universe);
        group = (EditText) findViewById(R.id.reg_group);

        reg = (Button) findViewById(R.id.reg_start);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otv = db();
                if (otv.length() == 7){
                    Toast.makeText(log_in.this, "Успешно", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(log_in.this, main.class);
                    startActivity(intent);
                }
                else {
                    if (otv.length() == 20) {
                        Toast.makeText(log_in.this, "Логин занят. Придумайте другой", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(log_in.this, "Что-то пошло не так :(", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }


    private String db(){
        zapros = new SQLregistration();
        zapros.start(login.getText().toString(),
                    password.getText().toString(),
                    name.getText().toString(),
                    secname.getText().toString(),
                    age.getText().toString(),
                    universe.getText().toString(),
                    group.getText().toString());

        try {
            zapros.join();
            dat = Datas.getInstance();
            dat.onCreate();
            dat.setDatas(login.getText().toString(), password.getText().toString());
            dat.saveDatas();

            return zapros.resResult();
        }
        catch (InterruptedException ie){
            Toast.makeText(this, "Сервер не доступен. Попробуйте позже", Toast.LENGTH_SHORT).show();
            return "Сервер не доступен. Попробуйте позже";
        }



    }


}
