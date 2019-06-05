package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "contacts.db";

    private static final String COLUMN_ID = "id";

    // TABLE contacts
    private static final String TABLE_USERS = "contacts";
    private static final String COLUMN_NAME_U = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_PERM = "perm";

    // TABLE products
    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_NAME_P = "name";
    private static final String COLUMN_STOCK = "stock";
    private static final String COLUMN_CATEGORIE = "categorie";

    // TABLE winkelmandje
    private static final String TABLE_WINKELMANDJE = "winkelmandje";
    private static final String COLUMN_NAME_W = "name";
    private static final String COLUMN_STOCK_W = "stock";

    SQLiteDatabase db;

    // queries for creating TABLES in SQLite
    private static final String TABLE_CREATE_USERS = "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , pass text not null, perm text not null);";

    private static final String TABLE_CREATE_PRODUCTS = "create table products (id integer primary key not null , " +
            "name text not null , stock integer not null , categorie text not null);";
    

    public DatabaseHelper(Context context)
    {
        super(context , DATABASE_NAME ,null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Database tables are created here with the queries
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_PRODUCTS);
        this.db = db;
    }


    public void insertProduct(Product_Database product_database){ // Func for inserting products in db
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Count existed rows in TABLE for defining the ID
        String query =  "select * from products";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        // Getting values from object en put it into row in db
        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME_P , product_database.getName());
        values.put(COLUMN_STOCK , product_database.getStock());
        values.put(COLUMN_CATEGORIE , product_database.getCategorie());

        db.insert(TABLE_PRODUCTS,null,values);  // van tuple values wordt een object aan de table name gevoegd
        db.close();

    }


    public void insertContact(Contact_Database c) {     // Func for inserting a contact in db
        ContentValues values = new ContentValues();


        String query =  "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME_U , c.getUsername());
        values.put(COLUMN_EMAIL , c.getEmail());
        values.put(COLUMN_PASS , c.getPassword());
        values.put(COLUMN_PERM, "User");           // Permissions bestaan uit: 'Admin', 'Beheerder' en 'User'

        db.insert(TABLE_USERS,null,values);
        db.close();
    }


    public Boolean checkMail(String given_mail){ // Check existance of a mail address at Register
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from contacts where email=?", new String[]{given_mail});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean checkUsername(String given_username){ // Check if username is already taken in db
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from contacts where name=?", new String[]{given_username});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean checkMatch(String username1, String password){ // Check if given username/password match correctly in db
        db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from contacts where name=? and pass=?", new String[]{username1, password});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkAdmin(String username1, String password1){
        db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from contacts where name=? and pass=? and perm='Admin'", new String[]{username1, password1});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public Boolean checkBeheerder(String username2, String password2){
        db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from contacts where name=? and pass=? and perm='Beheerder'", new String[]{username2, password2});
        if (cursor.getCount()>0) return true;
        else return false;
    }


    public Cursor GetProductByCat(String cat){
        db = this.getWritableDatabase();

        String query = "select * from products where categorie='"+cat+"'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    ArrayList<String> winkelmandje = new ArrayList<>();

    public void aanWinkelmandje(String item){
        winkelmandje.add(item);
    }


    public void eraseTable(){           // Func to clear a TABLE
        db = this.getReadableDatabase();
        db.execSQL("delete from products");
        db.execSQL("delete from contacts");

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
}