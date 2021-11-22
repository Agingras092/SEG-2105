package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CreateClass extends MainActivity {
    EditText classname, tod, classdesc, capacity;
    String classinst;
    Spinner spinner, difficulty, dow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        classinst    = getIntent().getStringExtra("name");
        classname    = (EditText)findViewById(R.id.editClassNameEdit);
        classdesc    = (EditText)findViewById(R.id.editClassDesc2);
        spinner      = (Spinner)findViewById(R.id.fitnessSpinner2);
        difficulty   = (Spinner)findViewById(R.id.difficultySpinner2);
        tod          = (EditText)findViewById(R.id.editClassName3);
        dow          = (Spinner)findViewById(R.id.daySpinner2);
        capacity     = (EditText)findViewById(R.id.editTextNumber);
        List<String> spinnerList = new ArrayList<String>();
        List<String> difficultyList = new ArrayList<String>();
        List<String> dayList = new ArrayList<String>();

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

        //Day of week
        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");
        dayList.add("Sunday");

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dayList);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dow.setAdapter(adapter3);
        //end day of week
    }

    public void addClass(View view) {
        Cursor res = myDB.verifyClassName(classname.getText().toString());
        Cursor res2 = myDB.verifyDay(dow.getSelectedItem().toString());

        if(res2.getCount() != 0 )
        {
            while(res2.moveToNext()) {
                if( res2.getString(4).equals(spinner.getSelectedItem().toString()) )
                {
                    Toast.makeText(CreateClass.this, "Type of class already on day taught by " + res2.getString(1), Toast.LENGTH_LONG).show();
                    //BONUS DELIVERABLE 2
                    return;
                }
            }
        }

        if(classname.getText().toString().equals(""))
        {
            Toast.makeText(CreateClass.this, "Empty class name.", Toast.LENGTH_LONG).show();
            return;
        }
        if(classdesc.getText().toString().equals(""))
        {
            Toast.makeText(CreateClass.this, "Empty class description.", Toast.LENGTH_LONG).show();
            return;
        }
        if(tod.getText().toString().equals(""))
        {
            //This isnt validated properly, however I dont understand pickers very well and don't want to set one up for this. So invalid
            //times are currently accepted.
            Toast.makeText(CreateClass.this, "Empty class time.", Toast.LENGTH_LONG).show();
            return;
        }

        if (res.getCount() == 0) {
            boolean isInserted = myDB.insertClass(classinst,
                                                  classname.getText().toString(),
                                                  classdesc.getText().toString(),
                                                  dow.getSelectedItem().toString(),
                                                  tod.getText().toString(),
                                                  difficulty.getSelectedItem().toString(),
                                                  spinner.getSelectedItem().toString(),
                                                  capacity.getText().toString());

            if (isInserted == true) {
                Toast.makeText(CreateClass.this, "Data inserted", Toast.LENGTH_LONG).show();
                finish();

            } else
                Toast.makeText(CreateClass.this, "Data not inserted", Toast.LENGTH_LONG).show();
        } else {
            showMessage("Class name already in use!", "Please select a different class name");
        }

    }

}