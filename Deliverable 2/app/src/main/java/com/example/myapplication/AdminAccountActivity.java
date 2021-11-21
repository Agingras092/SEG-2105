package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAccountActivity extends MainActivity {

    EditText username;
    EditText password;
    EditText rank;
    EditText name;
    EditText email;
    Button btnAddAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);

        username    = (EditText)findViewById(R.id.textUsername);
        password    = (EditText)findViewById(R.id.textPassword);
        rank        = (EditText)findViewById(R.id.textRank);
        email       = (EditText)findViewById(R.id.textEmail);
        name        = (EditText)findViewById(R.id.textName);
        btnAddAccount = (Button)findViewById(R.id.btnAddAccount);

        AddData();
    }

    public void AddData() {
        btnAddAccount.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = myDB.dbLogin(username.getText().toString());

                        if(res.getCount() == 0 )
                        {
                            boolean isInserted = myDB.insertAccount(username.getText().toString(),
                                    password.getText().toString(),
                                    rank.getText().toString(),
                                    email.getText().toString(),
                                    name.getText().toString() );

                            if( isInserted == true )
                            {
                                Toast.makeText(AdminAccountActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                                Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(myIntent);

                            }
                             else
                                Toast.makeText(AdminAccountActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            showMessage("Username already in use!", "Please select a different username");
                        }

                    }
                }
        );
    }
}