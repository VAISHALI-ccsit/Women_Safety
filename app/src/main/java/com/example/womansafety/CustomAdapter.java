package com.example.womansafety;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    private ArrayList<Contact> contacts;
    public CustomAdapter(ArrayList<Contact> contacts){
        this.contacts=contacts;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.sample_contact_recycleview, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     Contact contact = contacts.get(position);
     holder.textView.setText(contact.getDisplayName());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void updateContacts(ArrayList<Contact> newContacts){
        contacts.clear();
        contacts.addAll(newContacts);
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public MyViewHolder(@NonNull View v) {
            super(v);
            textView = v.findViewById(R.id.text_contact);
        }
    }
}
