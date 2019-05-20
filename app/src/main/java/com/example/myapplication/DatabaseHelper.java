package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , pass text not null);";


    public DatabaseHelper(Context context)
    {
        super(context , DATABASE_NAME ,null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact_Database c) {     // Functie om een contact toe te voegen in de database
        db = this.getWritableDatabase();                // Openen van de database
        ContentValues values = new ContentValues();

        String query =  "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();                  // Count wordt gebruikt om te tellen welk id de colum moet krijgen

        values.put(COLUMN_ID, count);                   // values wordt een een tuple met (COLUMN_ID = count, ...)
        values.put(COLUMN_NAME , c.getUsername());
        values.put(COLUMN_EMAIL , c.getEmail());
        values.put(COLUMN_PASS , c.getPassword());

        db.insert(TABLE_NAME,null,values);  // van tuple values wordt een object aan de table name gevoegd
        db.close();
    }







//    public Boolean checkUserExistance(String given_username)
//    {
//        db = this.getReadableDatabase();
//
//        String query = "select username from contacts";
//        Cursor c = db.rawQuery(query,null);
//
//        int i = 0;
//        if (c.getCount() > 0)
//        {
//            c.moveToFirst();
//            do {
//                id[i] = c.getInt(c.getColumnIndex("field_name"));
//                i++;
//            } while (c.moveToNext());
//            c.close();
//        }
//
//    }


    public Boolean checkMail(String given_mail){
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?", new String[]{given_mail});
        if (cursor.getCount()>0) return false;
        else return true;
    }





    public String searchPass(String username)
    {
        db = this.getReadableDatabase();
        String query = "select username, pass from "+TABLE_NAME + "where username ='" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return null;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}