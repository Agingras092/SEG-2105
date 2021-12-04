package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class InstructorMainActivity extends MainActivity {
    String name;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main);

        username = getIntent().getStringExtra("username");
        name = getIntent().getStringExtra("name");

        //Welcome message
        showMessage("Welcome!!!", "Welcome " + name + " / " + username + ". You are signed in as Instructor.");
    }

    public void createClassActivity(View view) {
        Intent myIntent = new Intent(InstructorMainActivity.this, CreateClass.class);
        myIntent.putExtra("name", name); //Optional parameters
        InstructorMainActivity.this.startActivity(myIntent);
    }

    public void searchClass(View view) {
        Intent myIntent = new Intent( InstructorMainActivity.this, SearchClass.class);
        InstructorMainActivity.this.startActivity(myIntent);
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

    public void deleteClassActivity(View view) {
        Intent myIntent2 = new Intent(InstructorMainActivity.this, DeleteClass.class);
        myIntent2.putExtra("name", name); //Optional parameters
        InstructorMainActivity.this.startActivity(myIntent2);
    }

    public void editClassActivity(View view) {
        Intent myIntent = new Intent(InstructorMainActivity.this, EditClass.class);
        InstructorMainActivity.this.startActivity(myIntent);
    }

}
