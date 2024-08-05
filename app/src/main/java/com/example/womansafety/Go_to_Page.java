package com.example.womansafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Go_to_Page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_page);

    }

    public void loginAsWomen(View view) {
        Intent intent = new Intent(Go_to_Page.this,LoginWoman.class);
        startActivity(intent);

    }

    public void loginAsFamily(View view) {
        Intent intent = new Intent(Go_to_Page.this,LoginFamily.class);
        startActivity(intent);

    }

    public void loginAsAdmin(View view) {
        Intent intent = new Intent(Go_to_Page.this,LoginAdmin.class);
        startActivity(intent);
    }
}