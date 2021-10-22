package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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
    Button btnAddAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);

        username    = (EditText)findViewById(R.id.textUsername);
        password    = (EditText)findViewById(R.id.textPassword);
        rank        = (EditText)findViewById(R.id.textRank);
        btnAddAccount = (Button)findViewById(R.id.btnAddAccount);

        AddData();
    }

    public void AddData() {
        btnAddAccount.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(username.getText().toString(),
                                        password.getText().toString(),
                                        rank.getText().toString() );

                        if( isInserted == true )
                            Toast.makeText(AdminAccountActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AdminAccountActivity.this, "Data not inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}