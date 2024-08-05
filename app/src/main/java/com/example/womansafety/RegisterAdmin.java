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

public class RegisterAdmin extends AppCompatActivity {
    EditText AdminRegName,AdminRegPhone,AdminRegEmail,AdminRegPassword,AdminRegCon_Password;
    TextView AdminRegLoginRedirect;
    FirebaseDatabase database;
    DatabaseReference reference;
    String names,phone_number,email,password,con_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);
        AdminRegName = findViewById(R.id.AdminRegName);
        AdminRegPhone = findViewById(R.id.AdminRegPhone);
        AdminRegEmail = findViewById(R.id.AdminRegEmail);
        AdminRegPassword = findViewById(R.id.AdminRegPassword);
        AdminRegCon_Password = findViewById(R.id.AdminRegCon_Password);
        AdminRegLoginRedirect = findViewById(R.id.AdminRegLoginRedirect);
    }

    public void goLoginAdmin(View view) {
        Intent intent = new Intent(RegisterAdmin.this,LoginAdmin.class);
        startActivity(intent);
    }

    public void registerAdmin(View view) {

        checkPassword();

    }
    public void checkPassword()
    {
        names = AdminRegName.getText().toString().trim();
        phone_number = AdminRegPhone.getText().toString().trim();
        email = AdminRegEmail.getText().toString().trim();
        password = AdminRegPassword.getText().toString().trim();
        con_password = AdminRegCon_Password.getText().toString().trim();

        if(names.isEmpty()){
            AdminRegName.setError("Name can't be empty");
        } else if (phone_number.isEmpty()) {
            AdminRegPhone.setError("Phone Number Can't be empty");
        } else if (phone_number.length() != 10) {
            AdminRegPhone.setError("Phone Number must be 10 digits");
        } else if (email.isEmpty()) {
            AdminRegEmail.setError("E-mail can't be empty");
        } else if (password.isEmpty()) {
            //Toast.makeText(this, "Password cannot be null", Toast.LENGTH_SHORT).show();
            AdminRegPassword.setError("Password can't be empty");
        } else if (password.length() < 8 ) {
            AdminRegPassword.setError("Password must be at least 8 characters");
        } else if (con_password.isEmpty()) {
            AdminRegCon_Password.setError("Confirm Password can't be empty");
        } else if (con_password.length() < 8) {
            AdminRegCon_Password.setError("Password must be at least 8 characters");
        } else if (!password.equals(con_password)) {
            //Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            AdminRegCon_Password.setError("Passwords don't match");
        } else {
            // Your existing code for Firebase database operations
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("admins");

            HelperClass helperClass = new HelperClass(names, phone_number, email, password, con_password);
            reference.child(names).setValue(helperClass);

            Toast.makeText(RegisterAdmin.this, "You have registered successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegisterAdmin.this, LoginAdmin.class);
            startActivity(intent);
            finish();
        }
    }
}