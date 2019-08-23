package com.nz.study;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SQLregistration extends Thread{
    String login = "11";
    String password ="11";
    String name = "11";
    String SecName = "11";
    String Age = "0";
    String University = "None";
    String Group = "none";
    String error = "null";

    InputStream is = null;
    String result = null;
    String line = null;

    public void run(){
        //Создвем лист для отправки запросов
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        //параметры для запроса
        nameValuePairs.add(new BasicNameValuePair("Login", login));
        nameValuePairs.add(new BasicNameValuePair("Password",password));
        nameValuePairs.add(new BasicNameValuePair("Name", name));
        nameValuePairs.add(new BasicNameValuePair("SecName",SecName));
        nameValuePairs.add(new BasicNameValuePair("Age", Age));
        nameValuePairs.add(new BasicNameValuePair("University", University));
        nameValuePairs.add(new BasicNameValuePair("Group", Group));


        //подключаемся к php и передаем параметры
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://vuzshedule.000webhostapp.com/PHP/registration.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
        }

       // получаем ответ от php запроса в формате json
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String sb = "";
            while ((line = reader.readLine()) != null) {
                sb = line;
            }
            is.close();
            result = sb;
        } catch (Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

       // обрабатываем полученный json
        try
        {
            JSONObject json_data = new JSONObject(result);
            error = (json_data.getString("Res"));

        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    public void start(String login, String password, String Name, String SecName, String Age, String University, String Group){
        this.login = login;
        this.password = password;
        this.name = Name;
        this.SecName = SecName;
        this.Age = Age;
        this.University = University;
        this.Group = Group;
        this.start();
    }

    public String resResult(){
        return error;
    }
}
