package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StudentEnroll extends MainActivity {

    /* Assumption that all classes are an hour long! */

    DatabaseHelper myDB;
    EditText classname;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enroll);

        username = getIntent().getStringExtra("username");

        myDB = new DatabaseHelper(this);

        classname = (EditText)findViewById(R.id.editTextTextPersonName2);
    }

    public void enroll(View view)
    {
        Cursor res = myDB.getStudentFromUsername(username);
        res.moveToNext();

        String studentid = res.getString(0);


        res = myDB.verifyClassName(classname.getText().toString());
        if(res.getCount() == 0)
        {
            Toast.makeText(StudentEnroll.this, "Class does not exist", Toast.LENGTH_LONG).show();
            return;
        }

        res = myDB.getAllClassesByClass(classname.getText().toString());
        res.moveToNext();

        String classid = res.getString(0);

        if( !classCapacityCheck(classid) || !classTimeCheck(studentid, classid))
        {
            return;
        }

        boolean isInserted = myDB.enrollStudent(studentid, classid);

        if (isInserted == true) {
            Toast.makeText(StudentEnroll.this, "Enrolled!", Toast.LENGTH_LONG).show();
            finish();
        } else
            Toast.makeText(StudentEnroll.this, "Not enrolled.", Toast.LENGTH_LONG).show();
    }

    public boolean classCapacityCheck(String classid)
    {
        int capacity, currentlyEnrolled;

        Cursor res = myDB.getAllClassesById(classid);
        res.moveToNext();

        capacity = Integer.parseInt(res.getString(8));

        res = myDB.getEnrollmentsByClass(classid);

        currentlyEnrolled = res.getCount();

        if(currentlyEnrolled >= capacity)
        {
            Toast.makeText(StudentEnroll.this, "Class is full", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public boolean classTimeCheck(String studentid, String classid)
    {
        Cursor res = myDB.getAllClassesById(classid);

        res.moveToNext();

        String classday = res.getString(5);
        int classtime = toMins(res.getString(6));

        res = myDB.getEnrollmentsByStudent(studentid);
        Cursor res2;

        while(res.moveToNext()) {
            res2 = myDB.getAllClassesById(res.getString(2));
            res2.moveToNext();
            if(res2.getString(5).equals(classday))
            {
                if( classtime <= toMins(res2.getString(6)) - 60 || classtime >= toMins(res2.getString(6) + 60) )
                {

                }
                else
                {
                    Toast.makeText(StudentEnroll.this, "Class conflicts with your " + res2.getString(2) + " class.", Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        }

        return true;
    }

    public static int toMins(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int hoursInMins = hour * 60;
        return hoursInMins + mins;
    }
}