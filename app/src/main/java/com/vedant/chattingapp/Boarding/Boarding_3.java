package com.vedant.chattingapp.Boarding;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vedant.chattingapp.Activities.login_Activity;
import com.vedant.chattingapp.R;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class Boarding_3 extends Fragment {


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_boarding3, container, false);

        root.findViewById(R.id.fab4).setOnClickListener(v -> startActivity(new Intent(getActivity(), login_Activity.class)));

        return root;
    }
}
