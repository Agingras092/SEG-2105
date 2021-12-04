package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username    = (EditText)findViewById(R.id.editUsername);
        password    = (EditText)findViewById(R.id.editPassword);

        myDB = new DatabaseHelper(this);
        //THIS IS CALLED MORE OFTEN THAN IT SHOULD BE BECAUSE OF THE WAY WE EXIT OUT OF THE CREATE ACCOUNT
        //AND CREATE CLASS AND DELETE CLASS AND DELETE ACCOUNT PAGES. WE KNOW WHY IT HAPPENS
    }

    public void OnLogin(View view)
    {
        Cursor res = myDB.dbLogin(username.getText().toString());

        if(res.getCount() == 0 )
        {
            showMessage("ERROR", "No user matching this username found");
            return;
        }
        while(res.moveToNext()) {
            if(res.getString(4).equals(password.getText().toString()))
            {
                //Find out what type of user account this is, then create an instance of that account and populate
                //with relevant data from the database.
                //Intent myIntent = new Intent(MainActivity.this, AdminAccountActivity.class);
                //MainActivity.this.startActivity(myIntent);
                //showMessage("It worked!", "Password accepted!");

                if(res.getString(5).equals("0") )
                {
                    //ADMIN CASE
                    Intent myIntent = new Intent(MainActivity.this, ActivityAdminMain.class);
                    //Validate login
                    myIntent.putExtra("username", res.getString(1)); //Optional parameters
                    myIntent.putExtra("name", res.getString(3)); //Optional parameters

                    MainActivity.this.startActivity(myIntent);
                }

                if(res.getString(5).equals("1") )
                {
                    //INSTRUCTOR CASE
                    Intent myIntent = new Intent(MainActivity.this, InstructorMainActivity.class);
                    //Validate login
                    myIntent.putExtra("username", res.getString(1)); //Optional parameters
                    myIntent.putExtra("name", res.getString(3)); //Optional parameters

                    MainActivity.this.startActivity(myIntent);
                }

                if(res.getString(5).equals("2") )
                {
                    //GYM STUDENT CASE
                    Intent myIntent = new Intent(MainActivity.this, InstructorMainActivity.class);
                    //Validate login
                    myIntent.putExtra("username", res.getString(1)); //Optional parameters
                    myIntent.putExtra("name", res.getString(3)); //Optional parameters

                    MainActivity.this.startActivity(myIntent);
                }

                return;
            }
        }

        return;
    }

    public void OnNewAccount(View view)
    {
            Intent myIntent = new Intent(MainActivity.this, AdminAccountActivity.class);
            //Validate login
            //myIntent.putExtra("database", myDB); //Optional parameters
            MainActivity.this.startActivity(myIntent);
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}