package com.example.womansafety;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

public class Guidelines_Fragment extends Fragment {


    WebView webview_safety_tips;
    public Guidelines_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guidelines_, container, false);
        webview_safety_tips=view.findViewById(R.id.webview_safety_tips);
        webview_safety_tips.setWebViewClient(new WebViewClient());
        webview_safety_tips.loadUrl("https://www.placer.ca.gov/8848/Violence-against-women-general-safety-ti");

        WebSettings webSettings = webview_safety_tips.getSettings();
        webSettings.setJavaScriptEnabled(true);
        return view;
    }
}