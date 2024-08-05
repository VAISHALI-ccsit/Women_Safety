package com.example.womansafety;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GovernmentHelp extends AppCompatActivity {

    WebView national_commission,women_safety_division,
            forbes_india,pib_article, women_and_child_development;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government_help);

        //2nd
        women_safety_division=findViewById(R.id.women_safety_division);
        women_safety_division.setWebViewClient(new WebViewClient());
        women_safety_division.loadUrl("https://www.mha.gov.in/en/divisionofmha/women-safety-division");

        WebSettings webSettings2 = women_safety_division.getSettings();
        webSettings2.setJavaScriptEnabled(true);

        //3rd
        forbes_india=findViewById(R.id.forbes_india);
        forbes_india.setWebViewClient(new WebViewClient());
        forbes_india.loadUrl("https://www.forbesindia.com/article/brand-connect/government-initiatives-for-womens-safety-best-practices/79383/1");

        WebSettings webSettings3 = forbes_india.getSettings();
        webSettings3.setJavaScriptEnabled(true);

        //4th
        pib_article=findViewById(R.id.pib_article);
        pib_article.setWebViewClient(new WebViewClient());
        pib_article.loadUrl("https://pib.gov.in/Pressreleaseshare.aspx?PRID=1575574");

        WebSettings webSettings4 = pib_article.getSettings();
        webSettings4.setJavaScriptEnabled(true);

        //5th
        women_and_child_development=findViewById(R.id.women_and_child_development);
        women_and_child_development.setWebViewClient(new WebViewClient());
        women_and_child_development.loadUrl("https://www.india.gov.in/official-website-ministry-women-and-child-development-0");

        WebSettings webSettings5 = women_and_child_development.getSettings();
        webSettings5.setJavaScriptEnabled(true);

    }
}