package com.example.android_experiment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MapViewFragment extends Fragment {
    public static WebViewFragment newInstance() {
        Bundle args=new Bundle();
        WebViewFragment fragment =new WebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle SaveInstanceState) {
        return inflater.inflate(R.layout.book_fragment_tab,container,false);
    }
    @Override
    public void onStart(){
        super.onStart();
    }
}
