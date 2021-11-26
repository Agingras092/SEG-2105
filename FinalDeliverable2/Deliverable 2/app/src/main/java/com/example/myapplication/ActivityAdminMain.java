package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

public class ActivityAdminMain extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        String username = getIntent().getStringExtra("username");
        String name = getIntent().getStringExtra("name");

        showMessage("Welcome!!!", "Welcome " + name + " / " + username + ". You are signed in as Admin.");
    }

    public void deleteAccount(View view)
    {
        Intent myIntent = new Intent(ActivityAdminMain.this, DeleteAccount.class);
        ActivityAdminMain.this.startActivity(myIntent);
    }

    public void viewAllAccounts(View view) {
        Cursor res = myDB.getAllAccounts();
        if(res.getCount() == 0) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("\nId : " + res.getString(0)+"\n");
            buffer.append("Username : " + res.getString(1)+"\n");
            buffer.append("Name : " + res.getString(2)+"\n");
            buffer.append("Email : " + res.getString(3)+"\n");
            buffer.append("Password : " + res.getString(4)+"\n");
            buffer.append("Rank : " + res.getString(5)+"\n");
        }
        showMessage("Data", buffer.toString());
    }

    //Deprecated
    public void viewAllClasses(View view) {
        Cursor res = myDB.getAllClasses();
        if(res.getCount() == 0) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()) {
            buffer.append("\nId : " + res.getString(0)+"\n");
            buffer.append("Class Name : " + res.getString(1)+"\n");
            buffer.append("Class Desc : " + res.getString(2)+"\n");
        }
        showMessage("Data", buffer.toString());
    }

    public void createFitnessActivity(View view) {
        Intent myIntent = new Intent(ActivityAdminMain.this, CreateFitness.class);
        ActivityAdminMain.this.startActivity(myIntent);
    }

    public void deleteClassActivity(View view) {
        Intent myIntent2 = new Intent(ActivityAdminMain.this, DeleteClass.class);
        ActivityAdminMain.this.startActivity(myIntent2);
    }

    public void returnToHomeScreen(View view) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        ActivityAdminMain.this.startActivity(myIntent);
    }
}