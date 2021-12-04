package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentMainActivity extends MainActivity {
    String name;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_main);

        username = getIntent().getStringExtra("username");
        name = getIntent().getStringExtra("name");

        //Welcome message
        showMessage("Welcome!!!", "Welcome " + name + " / " + username + ". You are signed in as Member.");
    }

    public void viewAllClasses(View view) {
        Cursor res = myDB.getAllClasses();
        if(res.getCount() == 0) {
            showMessage("Data", "No classes to display");
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("Class Instructor : " + res.getString(1)+"\n");
            buffer.append("Class Name : " + res.getString(2)+"\n");
            buffer.append("Time : " + res.getString(5) + " at " + res.getString(6)+"\n");
            buffer.append("Type : " + res.getString(4)+"\n");
            buffer.append("________________________________________\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void searchClass(View view) {
        Intent myIntent = new Intent( StudentMainActivity.this, StudentSearchClass.class);
        StudentMainActivity.this.startActivity(myIntent);
    }

    public void enroll(View view) {
        Intent myIntent = new Intent(StudentMainActivity.this, StudentEnroll.class);
        myIntent.putExtra("username", username); //Optional parameters
        StudentMainActivity.this.startActivity(myIntent);
    }


    public void viewMyClasses(View view)
    {
        Cursor res = myDB.getStudentFromUsername(username);
        res.moveToNext();

        res = myDB.getEnrollmentsByStudent(res.getString(0));
        if(res.getCount() == 0) {
            showMessage("Data", "No classes to display");
        }
        StringBuffer buffer = new StringBuffer();
        Cursor res2;
        while(res.moveToNext()) {
            res2 = myDB.getAllClassesById(res.getString(2));
            res2.moveToNext();
            buffer.append("Class Instructor : " + res2.getString(1)+"\n");
            buffer.append("Class Name : " + res2.getString(2)+"\n");
            buffer.append("Time : " + res2.getString(5) + " at " + res2.getString(6)+"\n");
            buffer.append("Type : " + res2.getString(4)+"\n");
            buffer.append("________________________________________\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void unenroll(View view)
    {
        Intent myIntent = new Intent(StudentMainActivity.this, StudentUnenroll.class);
        myIntent.putExtra("username", username); //Optional parameters
        StudentMainActivity.this.startActivity(myIntent);
    }

}