package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchClass extends MainActivity {

    DatabaseHelper myDB;
    EditText className;
    EditText instName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_class);

        className    = (EditText)findViewById(R.id.editTextClassName);
        instName    = (EditText)findViewById(R.id.editTextInstName);

        myDB = new DatabaseHelper(this);
    }

    public void searchClass(View view) {

        Cursor res;
        StringBuffer buffer = new StringBuffer();

        if( !className.getText().toString().equals("") )
        {
            res = myDB.getAllClassesByClass(className.getText().toString());
        }
        else if( !instName.getText().toString().equals("") )
        {
            res = myDB.getAllClassesByInst(instName.getText().toString());
        }
        else
        {
            buffer.append("Please enter information into one of the text boxes.");
            showMessage("Data", buffer.toString());
            return;
        }

        if(res.getCount() == 0) {
            showMessage("Data", "There are no classes to display.");
            return;
        }

        while(res.moveToNext()) {
            buffer.append("\nId : " + res.getString(0)+"\n");
            buffer.append("Class Instructor : " + res.getString(1)+"\n");
            buffer.append("Class Name : " + res.getString(2)+"\n");
            buffer.append("Class Desc : " + res.getString(3)+"\n");
        }

        showMessage("Data", buffer.toString());
    }
}