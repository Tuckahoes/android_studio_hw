package com.example.android_experiment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WebViewFragment extends Fragment {
    public static WebViewFragment newInstance() {
        Bundle args=new Bundle();
        WebViewFragment fragment =new WebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle SaveInstanceState) {
        View view = inflater.inflate(R.layout.web_fragment_tab,container,false);
        WebView webView=(WebView) view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("news.sina.cn");
        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
    }
}

