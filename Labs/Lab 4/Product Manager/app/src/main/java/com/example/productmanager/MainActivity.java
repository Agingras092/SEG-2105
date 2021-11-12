package com.example.productmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView productID;
    EditText productName;
    EditText productPrice;

    ListView productList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set variables to the IDs of .xml elements
        productID = (TextView) findViewById(R.id.txtProdID);
        productName = (EditText) findViewById(R.id.txtProdName);
        productPrice = (EditText) findViewById(R.id.txtProdPrice);
        productList = (ListView) findViewById(R.id.lstProdList);

        MyDBHelper dbHelper = new MyDBHelper(this);
        listItem = new ArrayList<>();

        // Call the viewData() method to display the existing products
        viewData();

        // When a product int he list is clicked, a toast is displayed with the name of the product
        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = productList.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, "" + text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void newProduct (View view) {
        MyDBHelper dbHelper = new MyDBHelper(this);

        // Get price from the text box
    }
}