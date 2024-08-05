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

public class RegisterFamily extends AppCompatActivity {
    EditText FamilyRegName,FamilyRegPhone,FamilyRegEmail,FamilyRegPassword,FamilyRegCon_Password;
    TextView FamilyRegLoginRedirect;
    FirebaseDatabase database;
    DatabaseReference reference;
    String names,phone_number,email,password,con_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_family);
        FamilyRegName = findViewById(R.id.FamilyRegName);
        FamilyRegPhone = findViewById(R.id.FamilyRegPhone);
        FamilyRegEmail =findViewById(R.id.FamilyRegEmail);
        FamilyRegPassword = findViewById(R.id.FamilyRegPassword);
        FamilyRegCon_Password = findViewById(R.id.FamilyRegCon_Password);
        FamilyRegLoginRedirect = findViewById(R.id.FamilyRegLoginRedirect);
    }

    public void goLoginFamily(View view) {
        Intent intent = new Intent(RegisterFamily.this,LoginFamily.class);
        startActivity(intent);
    }

    public void registerFamily(View view) {

        checkPassword();
    }

    public void checkPassword() {
        names = FamilyRegName.getText().toString().trim();
        phone_number = FamilyRegPhone.getText().toString().trim();
        email = FamilyRegEmail.getText().toString().trim();
        password = FamilyRegPassword.getText().toString().trim();
        con_password = FamilyRegCon_Password.getText().toString().trim();

        if (names.isEmpty()) {
            FamilyRegName.setError("Name can't be empty");
        } else if (phone_number.isEmpty()) {
            FamilyRegPhone.setError("Phone Number Can't be empty");
        } else if (phone_number.length() != 10) {
            FamilyRegPhone.setError("Phone Number must be 10 digits");
        } else if (email.isEmpty()) {
            FamilyRegEmail.setError("E-mail can't be empty");
        } else if (password.isEmpty()) {
            //Toast.makeText(this, "Password cannot be null", Toast.LENGTH_SHORT).show();
            FamilyRegPassword.setError("Password can't be empty");
        } else if (password.length() < 8) {
            FamilyRegPassword.setError("Password must be at least 8 characters");
        } else if (con_password.isEmpty()) {
            FamilyRegCon_Password.setError("Confirm Password can't be empty");
        } else if (con_password.length() < 8) {
            FamilyRegCon_Password.setError("Password must be at least 8 characters");
        } else if (!password.equals(con_password)) {
            //Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            FamilyRegCon_Password.setError("Passwords don't match");
        } else {
            // Your existing code for Firebase database operations
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("families");

            HelperClass helperClass = new HelperClass(names, phone_number, email, password, con_password);
            reference.child(names).setValue(helperClass);

            Toast.makeText(RegisterFamily.this, "You have registered successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegisterFamily.this, LoginFamily.class);
            startActivity(intent);
            finish();
        }
    }
}