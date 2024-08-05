package com.example.womansafety;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccessContact extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton imageButton;
    ArrayList<Contact> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_contact);

        recyclerView =findViewById(R.id.recycle_add_contact);

        CustomAdapter cd = new CustomAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cd);


        imageButton = findViewById(R.id.add_contact);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//                startActivity(intent);

                ArrayList<Contact> contacts = getContacts();
                //saveContactsToDatabase(contacts);
                cd.updateContacts(contacts);
            }

            private ArrayList<Contact> getContacts() {
                contactsList = new ArrayList<>();

                Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

                if(cursor!=null && cursor.getCount() > 0){
                    while (cursor.moveToNext()){
                        @SuppressLint("Range") String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        contactsList.add(new Contact(displayName));
                    }
                    cursor.close();
                }
//            private void saveContactsToDatabase(ArrayList<Contact> contactsList){
//
//            }

                return contactsList;
            }
        });


    }


}