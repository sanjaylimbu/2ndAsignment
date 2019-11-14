package com.example.a2ndasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDisplay extends AppCompatActivity {
    TextView nametxt, dobtxt, gendertxt, countrytxt, emailtxt, phonetxt;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display);
        nametxt = findViewById(R.id.recievedname);
        dobtxt = findViewById(R.id.recieveddob);
        gendertxt = findViewById(R.id.recievedgender);
        countrytxt = findViewById(R.id.recievedcountry);
        emailtxt = findViewById(R.id.recievedemail);
        phonetxt = findViewById(R.id.recievedphone);

        imageView = findViewById(R.id.recievedimage);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String dob = intent.getStringExtra("dob");
        String gender = intent.getStringExtra("gender");
        String country = intent.getStringExtra("country");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");

        String image   = intent.getStringExtra("image");


        int imageID = Integer.valueOf(image);

        nametxt.setText(name);
        dobtxt.setText(dob);
        gendertxt.setText(gender);
        countrytxt.setText(country);
        emailtxt.setText(email);
        phonetxt.setText(phone);

        imageView.setImageResource(imageID);

    }
}
