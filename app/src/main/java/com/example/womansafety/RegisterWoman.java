package com.example.womansafety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterWoman extends AppCompatActivity {
    EditText WomanRegName,WomanRegPhone,WomanRegEmail,WomanRegPassword,WomanRegCon_Password;
    TextView WomanRegLoginRedirect;
    FirebaseDatabase database;
    DatabaseReference reference;
    String names,phone_number,email,password,con_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_women);
        WomanRegName = findViewById(R.id.WomanRegName);
        WomanRegPhone = findViewById(R.id.WomanRegPhone);
        WomanRegEmail = findViewById(R.id.WomanRegEmail);
        WomanRegPassword = findViewById(R.id.WomanRegPassword);
        WomanRegCon_Password = findViewById(R.id.WomanRegCon_Password);
        WomanRegLoginRedirect = findViewById(R.id.WomanRegLoginRedirect);
    }

    public void goLoginWoman(View view) {
        Intent intent = new Intent(RegisterWoman.this,LoginWoman.class);
        startActivity(intent);
    }

    public void registerWoman(View view) {
        checkPassword();
    }

    public void checkPassword() {
        names = WomanRegName.getText().toString().trim();
        phone_number = WomanRegPhone.getText().toString().trim();
        email = WomanRegEmail.getText().toString().trim();
        password = WomanRegPassword.getText().toString().trim();
        con_password = WomanRegCon_Password.getText().toString().trim();

        if(names.isEmpty()){
            WomanRegName.setError("Name can't be empty");
        } else if (phone_number.isEmpty()) {
            WomanRegPhone.setError("Phone Number Can't be empty");
        } else if (phone_number.length() != 10) {
            WomanRegPhone.setError("Phone Number must be 10 digits");
        } else if (email.isEmpty()) {
            WomanRegEmail.setError("E-mail can't be empty");
        } else if (password.isEmpty()) {
            //Toast.makeText(this, "Password cannot be null", Toast.LENGTH_SHORT).show();
            WomanRegPassword.setError("Password can't be empty");
        } else if (password.length() < 8) {
            WomanRegPassword.setError("Password must be at least 8 characters");
        } else if (con_password.isEmpty()) {
            WomanRegCon_Password.setError("Confirm Password can't be empty");
        } else if (con_password.length() < 8) {
            WomanRegCon_Password.setError("Password must be at least 8 characters");
        } else if (!password.equals(con_password)) {
            //Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            WomanRegCon_Password.setError("Passwords don't match");
        } else {
            // Your existing code for Firebase database operations
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("women");

            HelperClass helperClass = new HelperClass(names, phone_number, email, password, con_password);
            reference.child(names).setValue(helperClass);

            Toast.makeText(RegisterWoman.this, "You have registered successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegisterWoman.this, LoginWoman.class);
            startActivity(intent);
            finish();
        }
    }
}