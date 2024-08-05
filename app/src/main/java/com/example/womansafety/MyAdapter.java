package com.example.womansafety;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class MyAdapter extends FirebaseRecyclerAdapter<UserRecycle, MyAdapter.myViewHolder> {



    public MyAdapter(@NonNull FirebaseRecyclerOptions<UserRecycle> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull UserRecycle model) {

        holder.title.setText(model.getTitle());
        holder.text.setText(model.getText());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_family_push_notification_recycleview,parent,false);

        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

       TextView title, text;

       public myViewHolder(View itemview){
           super(itemview);
           title=itemview.findViewById(R.id.title_recycleview);
           text=itemview.findViewById(R.id.body_recycleview);
       }
   }
}
