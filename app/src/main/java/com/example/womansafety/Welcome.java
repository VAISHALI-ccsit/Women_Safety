package com.example.womansafety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void goCenter(View view) {
        Intent intent = new Intent(Welcome.this,Go_to_Page.class);
        startActivity(intent);
        finish();
    }
}