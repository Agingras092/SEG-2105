package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateFitness extends MainActivity {

    EditText fitnessname;
    EditText fitnessdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fitness);

        fitnessname    = (EditText)findViewById(R.id.editClassNameEdit);
        fitnessdesc    = (EditText)findViewById(R.id.editClassDesc2);
    }

    public void addFitness(View view) {

        Cursor res = myDB.verifyClassName(fitnessname.getText().toString());

        if (res.getCount() == 0) {
            boolean isInserted = myDB.insertFitness(fitnessname.getText().toString(),
                                                    fitnessdesc.getText().toString());

            if (isInserted == true) {
                Toast.makeText(CreateFitness.this, "Data inserted", Toast.LENGTH_LONG).show();
                finish();

            } else
                Toast.makeText(CreateFitness.this, "Data not inserted", Toast.LENGTH_LONG).show();
        } else {
            showMessage("Class name already in use!", "Please select a different class name");
        }

    }
}