package com.example.a2ndasignment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2ndasignment.Model.Person;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    EditText editTextphone, editTextname,editTextdob, editTextemail, editTextaddress;
    RadioGroup radioGroup;
    Spinner spinner;
    Button button, btnView;
    AutoCompleteTextView autoComplete;
    //    CalendarView calendarView;
    String name,gender, dob, country, image, email, phone;
    String[] images = {"ima1","ima2","ima3"};

    String[] countries = {"Select an Option","Nepal", "Bhutan", "India", "Srilanka", "Maldives", "Myanmar", "Pakistan", "Afganistan"};
    List<Person> personlist = new ArrayList<>();

    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,month);
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            String mydateformat = "dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateformat, Locale.getDefault());
            editTextdob.setText(simpleDateFormat.format(calendar.getTime()));
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextname    = findViewById(R.id.tbl_name);
        editTextemail   = findViewById(R.id.tbl_email);

        editTextdob     = findViewById(R.id.tbl_dob);
        radioGroup      = findViewById(R.id.tbl_gender);
        editTextphone   = findViewById(R.id.tbl_phone);
        spinner         = findViewById(R.id.spiinerdata);
        button          = findViewById(R.id.btn_submit);
        btnView         = findViewById(R.id.btn_view);

        autoComplete   = findViewById(R.id.userimage);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.select_dialog_item, images
        );
        autoComplete.setAdapter(stringArrayAdapter);
        autoComplete.setThreshold(1);

        int hidingItemIndex = 0;

        MyAdapter dataAdapter = new MyAdapter(this, R.layout.spinner_values, countries, hidingItemIndex);
        dataAdapter.setDropDownViewResource(R.layout.spinner_values);

//        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.spinner_values, countries);
        //using array adapter to pass the value into spinner_values.xml file.
        spinner.setAdapter(dataAdapter);//using set adapter to pass the value into spinner.
        //use listView instead of spinner.

        button.setOnClickListener(this);
        btnView.setOnClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        editTextdob.setOnClickListener(this);
        newSpinnerValue();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.tbl_male){
            gender = "Male";
        }

        if(checkedId == R.id.tbl_female){
            gender = "Female";
        }

        if(checkedId == R.id.tbl_other){
            gender = "Other";
        }

    }


    private void newSpinnerValue(){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        name    = editTextname.getText().toString();
        dob     = editTextdob.getText().toString();

        email   = editTextemail.getText().toString();
        phone   = editTextphone.getText().toString();
        image   = autoComplete.getText().toString();

        if(v.getId() == R.id.btn_submit) {

            if (validate()) {
                String uri = "@drawable/"+image;
                int resID = getResources().getIdentifier(uri,null,getPackageName());
                String imageID = String.valueOf(resID);

                personlist.add(new Person(name,dob,gender, country, email, phone,imageID));
                Toast.makeText(this, "Submitted Successfully", Toast.LENGTH_SHORT).show();
            }
        }
        if(v.getId() == R.id.btn_view) {

            Intent intent = new Intent(this, RvActivity.class);
            intent.putExtra("personlist", (Serializable) personlist);
            startActivity(intent);
        }




        if(v.getId() == R.id.tbl_dob){
            new DatePickerDialog(this, mydatepicker,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    //validation

    private boolean validate(){
        if(TextUtils.isEmpty(name)){
            editTextname.setError("Please Enter Name");
            editTextname.requestFocus();
            return false;

        }
        if(TextUtils.isEmpty(dob)){
            editTextdob.setError("Please Enter DoB");
            editTextdob.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please Enter Email");
            editTextaddress.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(country)){
            Toast.makeText(this, "Select Country", Toast.LENGTH_SHORT).show();
            return  false;
        }

        if(TextUtils.isEmpty(phone)){
            editTextphone.setError("Please Enter Phone Number");
            editTextphone.requestFocus();
            return false;
        }

        if(!TextUtils.isDigitsOnly(phone)){
            editTextphone.setError("Invalid Phone Number");
            editTextphone.requestFocus();
            return false;
        }
        return true;
    }



}
