package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class StudentSearchClass extends MainActivity {
    EditText classname;
    Spinner dow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_class);

        classname    = (EditText)findViewById(R.id.editTextTextPersonName);
        dow          = (Spinner)findViewById(R.id.spinner);

        List<String> dayList = new ArrayList<String>();
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
    }

    public void searchClass(View view) {

        Cursor res;
        StringBuffer buffer = new StringBuffer();

        if( !classname.getText().toString().equals("") )
        {
            res = myDB.getAllClassesByClass(classname.getText().toString());
        }
        else
        {
            res = myDB.getAllClassesByDay(dow.getSelectedItem().toString());
        }

        if(res.getCount() == 0) {
            showMessage("Data", "There are no classes to display.");
            return;
        }

        while(res.moveToNext()) {
            buffer.append("Class Name : " + res.getString(2)+"\n");
            buffer.append("Class Time : " + res.getString(5) + " at " + res.getString(6)+"\n");
            buffer.append("Class Diff : " + res.getString(7)+"\n");
            buffer.append("Class Cap  : " + res.getString(8)+"\n");
            buffer.append("Class Desc : " + res.getString(3)+"\n");

        }

        showMessage("Data", buffer.toString());
    }
}