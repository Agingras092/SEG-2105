package com.example.productmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.productmanager.Product;

import androidx.annotation.Nullable;

import com.example.productmanager.Product;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final int DATBASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMNS_PRODUCTNAME = "productname";
    private static final String COLUMN_PRICE = "price";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMNS_PRODUCTNAME + " TEXT,"
                + COLUMN_PRICE + " DOUBLE" +
                ")";

        sqLiteDatabase.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(Product product)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMNS_PRODUCTNAME, product.getProdName());
        values.put(COLUMN_PRICE, product.getProdPrice());

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public Product findProduct(String productName){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS
                + " WHERE " + COLUMNS_PRODUCTNAME
                + " = \"" + productName + "\"";

        Cursor cursor = db.rawQuery(query, null);

        Product product = new Product();

        if(cursor.moveToFirst()){
            product.setProdID(Integer.parseInt(cursor.getString(0)));
            product.setProdName(cursor.getString(1));
            product.setProdPrice(Double.parseDouble(cursor.getString(2)));
            cursor.close();
        }
        else
        {
            product = null;
        }
        db.close();
        return product;
    }

    public boolean deleteProduct(String productName){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PRODUCTS
                + " WHERE " + COLUMNS_PRODUCTNAME
                + " = \"" + productName + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            String idStr = cursor.getString(0);
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
        }

        return result;
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS;

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
