package com.example.a2ndasignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.a2ndasignment.Model.Person;


import java.util.ArrayList;
import java.util.List;

public class RvActivity extends AppCompatActivity {
    List<Person> personlist;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        recyclerView=findViewById(R.id.user);



        Intent intent = getIntent();
        personlist = (List<Person>) intent.getSerializableExtra("personlist");

        MyRvAdapter Adapter = new MyRvAdapter(personlist, RvActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(Adapter);
    }
}