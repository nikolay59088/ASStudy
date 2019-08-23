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

public class SQLzapros_fio extends Thread {

    String login = "11";
    String password ="11";
    String name = "11";
    String surname = "11";
    Integer age = 19;
    String universe = "none";
    String group = "none";
    InputStream is = null;
    String result = null;
    String line = null;

    public void run(){
        //Создаем лист для отправки запросов
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        //параметры для запроса
        nameValuePairs.add(new BasicNameValuePair("Login", this.login));
        nameValuePairs.add(new BasicNameValuePair("Password",this.password));

        //подключаемся к php и передаем параметры
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://vuzshedule.000webhostapp.com/PHP/get_fio.php");
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
            login = (json_data.getString("Login"));
            password=(json_data.getString("Password"));
            name = (json_data.getString("Name"));
            surname = (json_data.getString("SecName"));
            age = Integer.decode (json_data.getString("Age"));
            universe = (json_data.getString("University"));
            group = (json_data.getString("YourGroup"));
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

    public void start(String login, String password){
        this.login = login;
        this.password = password;
        this.start();
    }



    public String reslogin ()
    {
        return  login;
    }
    public String respassword()
    {
        return  password;
    }
    public String resname(){
        return name;
    }
    public String ressurname() { return surname; }
    public Integer resage() { return age; }
    public String resuniverse() { return universe; }
    public String resgroup() { return group; }

}
