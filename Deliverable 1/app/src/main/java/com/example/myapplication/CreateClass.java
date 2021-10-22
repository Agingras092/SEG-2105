package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateClass extends MainActivity {
    EditText classname;
    EditText classdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        classname    = (EditText)findViewById(R.id.editClassName);
        classdesc    = (EditText)findViewById(R.id.editClassDesc);
    }

    public void addClass(View view) {

        Cursor res = myDB.verifyClassName(classname.getText().toString());

        if (res.getCount() == 0) {
            boolean isInserted = myDB.insertClass(classname.getText().toString(),
                                                  classdesc.getText().toString());

            if (isInserted == true) {
                Toast.makeText(CreateClass.this, "Data inserted", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(getApplicationContext(), ActivityAdminMain.class);
                startActivity(myIntent);

            } else
                Toast.makeText(CreateClass.this, "Data not inserted", Toast.LENGTH_LONG).show();
        } else {
            showMessage("Class name already in use!", "Please select a different class name");
        }

    }

}