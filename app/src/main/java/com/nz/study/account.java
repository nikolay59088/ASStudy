package com.nz.study;




import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class account extends Fragment {

    RecyclerView recyclerView;
    String name, secname, age, universe, group;
    SQLzapros_fio zapros;
    String login, password;

    public account() {
    }

    public static account newInstance() {
        return new account();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.account, container, false);
        Datas datas = Datas.getInstance();
        login = datas.getLogin();
        password = datas.getPassword();
        db(login,password);

        recyclerView = (RecyclerView) view.findViewById(R.id.accSettings);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ArrayList<AccSettings> sett = new ArrayList<AccSettings>();
        sett.add(new AccSettings("Имя", name));
        sett.add(new AccSettings("Фамилия", secname));
        sett.add(new AccSettings("Возраст", age));
        sett.add(new AccSettings("Институт", universe));
        sett.add(new AccSettings("Учебная группа", group));



        AccountAdapter accountAdapter = new AccountAdapter(sett, getContext());
        recyclerView.setAdapter(accountAdapter);



        return view;
    }

    private void db(String login, String password){
        zapros = new SQLzapros_fio();
        zapros.start(login, password);

        try {
            zapros.join();
        }
        catch (InterruptedException ie){

        }

        name = zapros.resname();
        secname = zapros.ressurname();
        age = String.valueOf(zapros.resage());
        universe = zapros.resuniverse();
        group = zapros.resgroup();


    }
}
