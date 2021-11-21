package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteAccount extends MainActivity {

    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);
        username = (EditText)findViewById(R.id.editUsername);
    }

    public void deleteAccountBtn(View view)
    {
        boolean isDeleted = myDB.deleteAccount(username.getText().toString());

        if (isDeleted == true) {
            Toast.makeText(DeleteAccount.this, "Data removed", Toast.LENGTH_LONG).show();
//            Intent myIntent = new Intent(getApplicationContext(), ActivityAdminMain.class);
//            startActivity(myIntent);

        } else
            Toast.makeText(DeleteAccount.this, "Data not removed", Toast.LENGTH_LONG).show();
    }
}