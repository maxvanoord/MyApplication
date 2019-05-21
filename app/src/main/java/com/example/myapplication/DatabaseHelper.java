package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";

    private static final String COLUMN_ID = "id";

    private static final String TABLE_USERS = "contacts";
    private static final String COLUMN_NAME_U = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";

    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_NAME_P = "name";
    private static final String COLUMN_STOCK = "stock";
    private static final String COLUMN_CATEGORIE = "categorie";

    SQLiteDatabase db;

    private static final String TABLE_CREATE_USERS = "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , pass text not null);";

    private static final String TABLE_CREATE_PRODUCTS = "create table products (id integer primary key not null , " +
            "name text not null , stock integer , categorie text not null);";


    public DatabaseHelper(Context context)
    {
        super(context , DATABASE_NAME ,null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_PRODUCTS);
        this.db = db;
    }


    public void insertProduct(Product_Database c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        String query =  "select * from products";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();


        values.put(COLUMN_ID, count);                   // values wordt een een tuple met (COLUMN_ID = count, ...)
        values.put(COLUMN_NAME_P , c.getName());
        values.put(COLUMN_STOCK , c.getStock());
        values.put(COLUMN_CATEGORIE , c.getCategorie());

        db.insert(TABLE_PRODUCTS,null,values);  // van tuple values wordt een object aan de table name gevoegd
        db.close();

    }


    public void insertContact(Contact_Database c) {     // Functie om een contact toe te voegen in de database
        db = this.getWritableDatabase();                // Openen van de database
        ContentValues values = new ContentValues();


        String query =  "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();                  // Count wordt gebruikt om te tellen welk id de colum moet krijgen


        values.put(COLUMN_ID, count);                   // values wordt een een tuple met (COLUMN_ID = count, ...)
        values.put(COLUMN_NAME_U , c.getUsername());
        values.put(COLUMN_EMAIL , c.getEmail());
        values.put(COLUMN_PASS , c.getPassword());

        db.insert(TABLE_USERS,null,values);  // van tuple values wordt een object aan de table name gevoegd
        db.close();
    }


    public Boolean checkMail(String given_mail){
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from contacts where email=?", new String[]{given_mail});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean checkUsername(String given_username){
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from contacts where name=?", new String[]{given_username});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean checkMatch(String username1, String password){
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from contacts where name=? and pass=?", new String[]{username1, password});
        if (cursor.getCount()>0) return true;
        else return false;
    }


    public void eraseTable(){           // deze functie kan worden opgeroepen om een table te legen
        db = this.getReadableDatabase();
        db.execSQL("delete from contacts");
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        onCreate(db);
    }
}