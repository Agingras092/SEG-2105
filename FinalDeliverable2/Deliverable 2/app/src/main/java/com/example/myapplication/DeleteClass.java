package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteClass extends MainActivity {

    EditText classname;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_class);

        name = getIntent().getStringExtra("name");
        classname    = (EditText)findViewById(R.id.editClassName2);
    }

    public void deleteClass(View view) {

        Cursor res = myDB.verifyClassName(classname.getText().toString());

        if (res.getCount() != 0) {

            res = myDB.getAllClassesByClass(classname.getText().toString());
            res.moveToNext();
            if(!res.getString(1).equals(name))
            {
                Toast.makeText(DeleteClass.this, "Can't delete another instructors class", Toast.LENGTH_LONG).show();
            }
            else
            {
                boolean isDeleted = myDB.deleteClass(classname.getText().toString());

                if (isDeleted == true) {
                    Toast.makeText(DeleteClass.this, "Data removed", Toast.LENGTH_LONG).show();
                    finish();

                } else
                    Toast.makeText(DeleteClass.this, "Data not removed", Toast.LENGTH_LONG).show();
            }
        }
    }
}