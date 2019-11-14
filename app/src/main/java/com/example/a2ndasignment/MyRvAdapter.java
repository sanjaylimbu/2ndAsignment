package com.example.a2ndasignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2ndasignment.Model.Person;


import java.util.ArrayList;
import java.util.List;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder> {


private List<Person> personlist = new ArrayList<>();
private Context context;

public MyRvAdapter(List<Person> personlist, Context context) {
        this.personlist = personlist;
        this.context = context;
        }

@NonNull
@Override
public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_person,parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
        }

@Override
public void onBindViewHolder(@NonNull MyHolder holder, int position) {

final Person person = personlist.get(position);
        int imageID = Integer.valueOf(person.getImage());
        holder.imageView.setImageResource(imageID);
        holder.textView.setText(person.getName());


        holder.textView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent( v.getContext() , UserDisplay.class);
        intent.putExtra("name", person.getName());
        intent.putExtra("dob", person.getDob());
        intent.putExtra("gender", person.getGender());
        intent.putExtra("country", person.getCountry());
        intent.putExtra("email", person.getEmail());
        intent.putExtra("phone", person.getPhone());

        intent.putExtra("image", person.getImage());
        v.getContext().startActivity(intent);
        }
        });
        }

@Override
public int getItemCount() {
        return personlist.size();
        }



public class MyHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView textView;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.rvuserimage);
        textView  = itemView.findViewById(R.id.rvusername);
    }
}
}
