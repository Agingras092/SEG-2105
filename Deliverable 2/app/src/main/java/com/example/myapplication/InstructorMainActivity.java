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

    /*
        1. See all scheduled fitness classes
            a. List of all classes taught by instructor (DONE)
            b. Search for class by classname or instructorname (DONE)
        2. Schedule a class for this instructor
            a. Chooses type of fitness they teach (from admin created options) (DONE)
            b. Select difficulty level (DONE)
            c. Day of the week (DONE)
            d. Time the class will be at (DONE)
            e. Maximum capacity for class (DONE)
        3. editing instructors classes
            a. Cancel a class
        4. Cant schedule a boxing class on a day where another instructor already has a boxing class.(DONE)
           this applies for every class type... Should generate an error msg.
     */

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
        Cursor res = myDB.getAllClassesByInst(name);
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
        InstructorMainActivity.this.startActivity(myIntent2);
    }

    public void editClassActivity(View view) {
        Intent myIntent = new Intent(InstructorMainActivity.this, EditClass.class);
        InstructorMainActivity.this.startActivity(myIntent);
    }

}
