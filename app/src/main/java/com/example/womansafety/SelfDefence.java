package com.example.womansafety;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.VideoView;

public class SelfDefence extends AppCompatActivity {

  // VideoView videoView;
   WebView webView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_defence);


        webView1=findViewById(R.id.webview1);
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("https://www.videvo.net/stock-video-footage/self-defense/");

        webView1.setVerticalScrollBarEnabled(true);
        webView1.setHorizontalScrollBarEnabled(true);
        WebSettings webSettings3 = webView1.getSettings();
        webSettings3.setJavaScriptEnabled(true);



    }
}