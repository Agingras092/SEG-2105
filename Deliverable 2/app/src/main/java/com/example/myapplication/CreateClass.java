package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateClass extends MainActivity {
    EditText classname, tod, dow, classdesc;
    String classinst;
    Spinner spinner, difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        classinst    = getIntent().getStringExtra("name");
        classname    = (EditText)findViewById(R.id.editClassName);
        classdesc    = (EditText)findViewById(R.id.editClassDesc);
        spinner      = (Spinner)findViewById(R.id.fitnessSpinner);
        difficulty   = (Spinner)findViewById(R.id.difficultySpinner);
        tod          = (EditText)findViewById(R.id.editClassName3);
        dow          = (EditText)findViewById(R.id.editClassName4);
        List<String> spinnerList = new ArrayList<String>();
        List<String> difficultyList = new ArrayList<String>();

        //FitnessSpinner setup
        Cursor res = myDB.getAllFitness();
        if(res.getCount() == 0) {
            Toast.makeText(CreateClass.this, "No fitness types", Toast.LENGTH_LONG).show();
            finish();
        }

        while(res.moveToNext()) {
            spinnerList.add(res.getString(1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //End FitnessSpinner setup

        //DifficultySpinner
        difficultyList.add("Beginner");
        difficultyList.add("Intermediate");
        difficultyList.add("Advanced");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, difficultyList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter2);
        //End DifficultySpinner
    }

    public void addClass(View view) {
//        Cursor res = myDB.verifyClassName(classname.getText().toString());

//        if (res.getCount() == 0) {
//            boolean isInserted = myDB.insertClass(classinst,
//                                                  classname.getText().toString(),
//                                                  classdesc.getText().toString());
//
//            if (isInserted == true) {
//                Toast.makeText(CreateClass.this, "Data inserted", Toast.LENGTH_LONG).show();
//                finish();
//
//            } else
//                Toast.makeText(CreateClass.this, "Data not inserted", Toast.LENGTH_LONG).show();
//        } else {
//            showMessage("Class name already in use!", "Please select a different class name");
//        }

    }

}