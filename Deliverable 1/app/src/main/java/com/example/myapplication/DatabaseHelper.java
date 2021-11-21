package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "account_table";
    public static final String DATABASE_NAME = "Account.db";

    public static final String ID = "ID";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String RANK = "RANK";
    public static final String EMAIL = "EMAIL";
    public static final String NAME = "NAME";

    public static final String TABLE_NAME2 = "class_table";
    public static final String DATABASE_NAME2 = "Class.db";

    public static final String CLASSID = "ID";
    public static final String CLASSINST = "CLASSINST";
    public static final String CLASSNAME = "CLASSNAME";
    public static final String CLASSDESC = "CLASSDESC";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); //REMAKE DATABASE
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, NAME TEXT, EMAIL TEXT, PASSWORD TEXT, RANK INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, CLASSINST TEXT, CLASSNAME TEXT, CLASSDESC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertAccount(String username, String password, String rank, String name, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        contentValues.put(RANK, rank);
        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        if( result == -1 ) return false;

        return true;
    }

    public boolean insertClass(String classinst, String classname, String classdesc) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CLASSINST, classinst);
        contentValues.put(CLASSNAME, classname);
        contentValues.put(CLASSDESC, classdesc);

        long result = sqLiteDatabase.insert(TABLE_NAME2, null, contentValues);

        if( result == -1 ) return false;

        return true;
    }

    public boolean deleteClass(String classname) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CLASSNAME, classname);

        long result = sqLiteDatabase.delete(TABLE_NAME2, "classname=?", new String[]{classname});

        if( result == -1 ) return false;

        return true;
    }

    public boolean deleteAccount(String username)
    {
        Cursor res = getAllAccounts();

        if(res.getCount() <= 1) return false; //cant remove last account;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CLASSNAME, username);

        long result = sqLiteDatabase.delete(TABLE_NAME, "username=?", new String[]{username});

        if( result == -1 ) return false;

        return true;
    }

    public Cursor getAllAccounts() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getAllClasses() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME2, null);
        return res;
    }

    public Cursor dbLogin(String username) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from "+TABLE_NAME+" where username = '" + username + "'";
        Cursor res = sqLiteDatabase.rawQuery(query, null);
        return res;
    }

    public Cursor verifyClassName(String classname) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from "+TABLE_NAME2+" where classname = '" + classname + "'";
        Cursor res = sqLiteDatabase.rawQuery(query, null);
        return res;
    }

}
