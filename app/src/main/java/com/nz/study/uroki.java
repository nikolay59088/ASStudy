package com.nz.study;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class uroki extends Fragment {

    public uroki() {
    }

    public static uroki newInstance() {
        return new uroki();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.uroki, container, false);
    }
}
