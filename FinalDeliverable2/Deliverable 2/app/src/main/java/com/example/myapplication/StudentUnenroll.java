package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

public class StudentUnenroll extends MainActivity {

    EditText classname;
    String username;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_unenroll);

        username = getIntent().getStringExtra("username");

        classname    = (EditText)findViewById(R.id.editTextTextPersonName3);

        myDB = new DatabaseHelper(this);
    }

    public void unenroll(View view)
    {
        Cursor res = myDB.getStudentFromUsername(username);
        res.moveToNext();

        String studentid = res.getString(0);

        res = myDB.verifyClassName(classname.getText().toString());
        if(res.getCount() == 0)
        {
            Toast.makeText(StudentUnenroll.this, "Class does not exist", Toast.LENGTH_LONG).show();
            return;
        }

        res = myDB.getAllClassesByClass(classname.getText().toString());
        res.moveToNext();

        String classid = res.getString(0);

        boolean isInserted = myDB.unenrollStudent(studentid, classid);

        if (isInserted == true) {
            Toast.makeText(StudentUnenroll.this, "Unenrolled!", Toast.LENGTH_LONG).show();
            finish();
        } else
            Toast.makeText(StudentUnenroll.this, "Not unenrolled.", Toast.LENGTH_LONG).show();

        return;
    }
}