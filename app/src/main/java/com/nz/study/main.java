package com.nz.study;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class main extends AppCompatActivity {

    private Toolbar toolbar;
    private BottomNavigationView navigation;
    private Boolean flag = true;
    Fragment frag;
    FragmentTransaction ftrans;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_account:

                    loadFragment(com.nz.study.account.newInstance());
                    getSupportActionBar().setTitle("Профиль");
                    return true;
                case R.id.action_schedule:

                    loadFragment(uroki.newInstance());
                    getSupportActionBar().setTitle("Расписание");
                    return true;
                case R.id.action_settings:

                    loadFragment(com.nz.study.settings.newInstance());
                    getSupportActionBar().setTitle("Настройки");
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.okno, fragment);
        ft.commit();
    }

    private void removeFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Профиль");
        frag = new account();


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(com.nz.study.account.newInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ftrans = getSupportFragmentManager().beginTransaction();
        if(flag){
            item.setIcon(R.drawable.check);
            flag = !flag;
            ftrans.remove(frag);
            findViewById(R.id.acc_adapter_but).setVisibility(View.VISIBLE);
            ftrans.commit();
           // loadFragment(account.newInstance());
        }
        else{
            item.setIcon(R.drawable.settings);
            flag = !flag;
            removeFragment(frag);

            findViewById(R.id.acc_adapter_but).setVisibility(View.VISIBLE);
            //loadFragment(account.newInstance());
        }
        return true;



        }
    }
