package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditClass extends MainActivity {

    EditText classnameini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        classnameini = (EditText)findViewById(R.id.editClassName2);
    }

    Cursor res;

    public void editClassInit(View view)
    {
        res = myDB.verifyClassName(classnameini.getText().toString());

        if (res.getCount() != 0 ) {
            Intent myIntent = new Intent(getApplicationContext(), EditClassInformation.class);
            myIntent.putExtra("classname", classnameini.getText().toString()); //Optional parameters
            EditClass.this.startActivity(myIntent);
            return;
        } else
            Toast.makeText(EditClass.this, "Could not find class with this name.", Toast.LENGTH_LONG).show();

    }

}