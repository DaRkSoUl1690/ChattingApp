package com.vedant.chattingapp.tab_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vedant.chattingapp.R;

import androidx.fragment.app.Fragment;

public class call_Fragment extends Fragment {


    public call_Fragment() {
        // Required empty public constructor
    }


    public static void newInstance() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_, container, false);
    }
}
