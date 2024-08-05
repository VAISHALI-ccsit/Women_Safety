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

public class LoginFamily extends AppCompatActivity {
    EditText FamilyLogPhoneEmail,FamilyLogPassword;
    TextView FamilyLogForgetPassword,FamilyLogRegisterRedirect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_family);
        FamilyLogPhoneEmail = findViewById(R.id.FamilyLogPhoneEmail);
        FamilyLogPassword=findViewById(R.id.FamilyLogPassword);
        FamilyLogForgetPassword = findViewById(R.id.FamilyLogForgetPass);
        FamilyLogRegisterRedirect = findViewById(R.id.FamilyRegisterRedirect);
    }

    public void goRegisterFamily(View view) {
        Intent intent = new Intent(LoginFamily.this,RegisterFamily.class);
        startActivity(intent);
    }

    public void loginFamily(View view) {
        if (!validateNumber() | !validatePassword()) {

        } else {
            checkUser();
        }
    }
    public Boolean validateNumber() {
        String val = FamilyLogPhoneEmail.getText().toString().trim();
        if (val.isEmpty()) {
            FamilyLogPhoneEmail.setError("Phone Number can't empty !");
            return false;
        } else if (val.length() != 10) {
            FamilyLogPhoneEmail.setError("Phone Number must be 10 digits !");
            return false;
        } else {
            FamilyLogPhoneEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword()
    {
        String val = FamilyLogPassword.getText().toString().trim();
        if (val.isEmpty()) {
            FamilyLogPassword.setError("Password can't be empty !");
            return false;
        } else if (val.length() < 8) {
            FamilyLogPassword.setError("Password must be at least 8 characters !");
            return false;
        } else {
            FamilyLogPassword.setError(null);
            return true;
        }
    }

    public void checkUser()
    {
        String familyLogPhoneEmailVar = FamilyLogPhoneEmail.getText().toString().trim();
        String familyLogPasswordVar = FamilyLogPassword.getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("families");
        Query checkUserDatabase = databaseReference.orderByChild("phone_number").equalTo(familyLogPhoneEmailVar);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    FamilyLogPhoneEmail.setError(null);
                    String passwordFromDB = snapshot.child(familyLogPhoneEmailVar).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB, familyLogPasswordVar)) {
                        FamilyLogPhoneEmail.setError(null);
                        Intent intent = new Intent(LoginFamily.this,Demo.class);
                        startActivity(intent);
                    } else {
                        FamilyLogPassword.setError("Family, Invalid Credentials !");
                        FamilyLogPassword.requestFocus();
                    }
                } else
                {
                    FamilyLogPhoneEmail.setError("Family, does not exist !");
                    FamilyLogPhoneEmail.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}