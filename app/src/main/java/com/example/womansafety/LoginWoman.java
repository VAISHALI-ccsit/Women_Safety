package com.example.womansafety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginWoman extends AppCompatActivity {
    EditText WomanLogPhoneEmail,WomanLogPassword;
    TextView WomanLogForgetPassword,WomanLogRegisterRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_woman);
        WomanLogPhoneEmail = findViewById(R.id.WomanLogPhone);
        WomanLogPassword = findViewById(R.id.WomanLogPassword);
        WomanLogForgetPassword = findViewById(R.id.WomanLogForgetPass);
        WomanLogRegisterRedirect = findViewById(R.id.WomanRegisterRedirect);
    }

    public void goRegisterWoman(View view) {
        Intent intent = new Intent(LoginWoman.this, RegisterWoman.class);
        startActivity(intent);
        finish();
    }

    public void loginWoman(View view) {
        if (!validateNumber() | !validatePassword()) {

        } else {
            checkUser();
        }
    }

    public Boolean validateNumber() {
        String val = WomanLogPhoneEmail.getText().toString().trim();
        if (val.isEmpty()) {
            WomanLogPhoneEmail.setError("📱 Phone Number can't empty ❗");
            return false;
        } else if (val.length() != 10) {
            WomanLogPhoneEmail.setError("📲 Phone Number must be 10 digits ❗");
            return false;
        } else {
            WomanLogPhoneEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword()
    {
        String val = WomanLogPassword.getText().toString().trim();
        if (val.isEmpty()) {
            WomanLogPassword.setError("🔓 Password can't be empty ❗");
            return false;
        } else if (val.length() < 8) {
            WomanLogPassword.setError("🔐 Password must be at least 8 characters ❗");
            return false;
        } else {
            WomanLogPassword.setError(null);
            return true;
        }
    }

    public void checkUser()
    {
        String womanLogPhoneEmailVar = WomanLogPhoneEmail.getText().toString().trim();
        String womanLogPasswordVar = WomanLogPassword.getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("women");
        Query checkUserDatabase = databaseReference.orderByChild("phone_number").equalTo(womanLogPhoneEmailVar);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    WomanLogPhoneEmail.setError(null);
                    String passwordFromDB = snapshot.child(womanLogPhoneEmailVar).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB, womanLogPasswordVar)) {
                        WomanLogPhoneEmail.setError(null);
                        Intent intent = new Intent(LoginWoman.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        WomanLogPassword.setError("💃Woman, Invalid Credentials ❗");
                        WomanLogPassword.requestFocus();
                    }
                } else
                {
                    WomanLogPhoneEmail.setError("🙅‍♀️ Woman, does not exist ❗");
                    WomanLogPhoneEmail.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}