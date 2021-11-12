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
import com.example.productmanager.MyDBHelper;

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
        //Different than lab video, was the lab video code wrong?
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

    public void newProduct(View view) {
        MyDBHelper dbHelper = new MyDBHelper(this);

        double price = Double.parseDouble(productPrice.getText().toString());

        Product product = new Product(productName.getText().toString(), price);

        dbHelper.addProduct(product);

        productName.setText("");
        productPrice.setText("");

        listItem.clear();
        viewData();
    }

    public void lookuproduct(View view) {
        MyDBHelper dbHelper = new MyDBHelper(this);

        Product product = dbHelper.findProduct(productName.getText().toString());

        if(product != null)
        {
            productID.setText(String.valueOf(product.getProdId()));
            productPrice.setText(String.valueOf(product.getProdPrice()));
        }
        else
        {
            productID.setText(" No Match Found");
        }
    }

    public void removeProduct(View view) {
        MyDBHelper dbHelper = new MyDBHelper(this);

        boolean result = dbHelper.deleteProduct((productName.getText().toString()));

        listItem.clear();
        viewData();

        if( result )
        {
            productID.setText(" Record Deleted");
            productName.setText("");
            productPrice.setText("");
        }
        else
        {
            productID.setText(" No Match Found");
        }
    }

    private void viewData(){
        MyDBHelper dbHelper = new MyDBHelper(this);

        Cursor cursor = dbHelper.viewData();

        if(cursor.getCount() == 0 )
        {
            Toast.makeText(this, " No data to show", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItem);

            productList.setAdapter(adapter);
        }
    }
}