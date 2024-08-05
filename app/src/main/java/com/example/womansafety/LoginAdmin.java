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

public class LoginAdmin extends AppCompatActivity {
    EditText AdminLogPhoneEmail,AdminLogPassword;
    TextView AdminLogForgetPassword,AdminLogRegisterRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        AdminLogPhoneEmail = findViewById(R.id.AdminLogPhone);
        AdminLogPassword = findViewById(R.id.AdminLogPassword);
        AdminLogForgetPassword = findViewById(R.id.AdminLogForgetPass);
        AdminLogRegisterRedirect = findViewById(R.id.AdminRegisterRedirect);

    }

    public void goRegisterAdmin(View view) {
        Intent intent = new Intent(LoginAdmin.this,RegisterAdmin.class);
        startActivity(intent);
    }

    public void loginAdmin(View view) {
        if (!validateNumber() | !validatePassword()) {

        } else {
            checkUser();
        }
    }

// method declare for check the
    public Boolean validateNumber()
    {
        String val = AdminLogPhoneEmail.getText().toString().trim();
        if (val.isEmpty())
        {
            AdminLogPhoneEmail.setError("Phone Number or E-mail can't empty !");
            return false;
        } else if (val.length() != 10) {
            AdminLogPhoneEmail.setError("Phone Number must be 10 digits !");
            return false;
        } else {
            AdminLogPhoneEmail.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = AdminLogPassword.getText().toString().trim();
        if (val.isEmpty()) {
            AdminLogPassword.setError("Password can't be empty !");
            return false;
        } else if (val.length() < 8) {
            AdminLogPassword.setError("Password must be at least 8 characters !");
            return false;
        } else {
            AdminLogPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String adminLogPhoneEmailVar = AdminLogPhoneEmail.getText().toString().trim();
        String adminLogPasswordVar = AdminLogPassword.getText().toString().trim();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("admins");
        Query checkUserDatabase = databaseReference.orderByChild("phone_number").equalTo(adminLogPhoneEmailVar);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    AdminLogPhoneEmail.setError(null);
                    String passwordFromDB = snapshot.child(adminLogPhoneEmailVar).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB,adminLogPasswordVar) ) {
                        AdminLogPhoneEmail.setError(null);
                        Intent intent = new Intent(LoginAdmin.this,AdminDashboard.class);
                        startActivity(intent);
                    } else {
                        AdminLogPassword.setError("Admin, Invalid Credentials !");
                        AdminLogPassword.requestFocus();
                    }
                } else
                    {
                        AdminLogPhoneEmail.setError("😎Admin, does not exist !");
                        AdminLogPhoneEmail.requestFocus();
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}