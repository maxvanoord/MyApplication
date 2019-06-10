package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String loginKeeper = null;


    private static int DATABASE_VERSION = 6;
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
    private static final String COLUMN_AMOUNT = "amount";

    // TABLE reports
    private static final String TABLE_REPORTS = "reports";
    private static final String COLUMN_HUURDER = "huurder";
    private static final String COLUMN_ITEM = "items";
    private static final String COLUMN_OPHAAL = "ophaal";
    private static final String COLUMN_TERUGBRENG = "terugbreng";


    SQLiteDatabase db;


    // queries for creating TABLES in SQLite
    private static final String TABLE_CREATE_USERS = "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , pass text not null, perm text not null);";

    private static final String TABLE_CREATE_PRODUCTS = "create table products (id integer primary key not null , " +
            "name text not null , stock integer not null , categorie text not null);";

    private static final String TABLE_CREATE_WINKELMANDJE = "create table winkelmandje (id integer primary key not null , "
            + "name text not null, amount integer not null);";

    private static final String TABLE_CREATE_REPORTS = "create table reports (id integer primary key not null , "
            + "huurder text not null, items text not null, ophaal text not null, terugbreng text not null);";
    

    public DatabaseHelper(Context context)
    {
        super(context , DATABASE_NAME ,null , DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Database tables are created here with the queries
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_PRODUCTS);
        db.execSQL(TABLE_CREATE_WINKELMANDJE);
        db.execSQL(TABLE_CREATE_REPORTS);
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


    public void insertReport(Report_Database report_database){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Count existed rows in TABLE for defining the ID
        String query =  "select * from reports";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        // Getting values from object en put it into row in db
        values.put(COLUMN_ID, count);
        values.put(COLUMN_HUURDER , report_database.getHuurder());
        values.put(COLUMN_ITEM , report_database.getItems());
        values.put(COLUMN_OPHAAL , report_database.getOphaal());
        values.put(COLUMN_TERUGBRENG , report_database.getTerugbreng());

        db.insert(TABLE_REPORTS,null,values);  // van tuple values wordt een object aan de table name gevoegd
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


    public void insertWinkelmandje(Winkelmand_Database winkelmand_database) { // Func for inserting products in winkelmandje
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Count existed rows in TABLE for defining the ID
        String query = "select * from winkelmandje";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        // Getting values from object en put it into row in db
        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME_W, winkelmand_database.getName());
        values.put(COLUMN_AMOUNT, winkelmand_database.getAmount());


        db.insert(TABLE_WINKELMANDJE, null, values);  // van tuple values wordt een object aan de table name gevoegd
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


    public Cursor GetWinkelmandProducts(){
        db = this.getWritableDatabase();

        String query = "select * from winkelmandje";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }


    public void eraseTables(){           // Func to clear a TABLE
        db = this.getReadableDatabase();
        db.execSQL("delete from products");
        db.execSQL("delete from contacts");
        db.execSQL("delete from winkelmandje");
        db.execSQL("delete from reports");
    }


    public void clearWinkelmandje(){
        db = this.getWritableDatabase();
        db.execSQL("delete from winkelmandje");
    }

    public void insertAllProducts(){
        db = this.getWritableDatabase();
        db.execSQL("delete from products");
        Product_Database item1 = new Product_Database("HoloLens", 2, "Virtual Reality");
        Product_Database item2 = new Product_Database("Playstation 4", 1, "Games");
        Product_Database item3 = new Product_Database("LCD Display", 5, "Computer");
        Product_Database item4 = new Product_Database("Computer Case", 4, "Computer");
        Product_Database item5 = new Product_Database("Computer", 5, "Computer");
        Product_Database item6 = new Product_Database("AR Drone Elite Edition", 7, "Drones");
        Product_Database item7 = new Product_Database("Vandal", 1, "Overig");
        Product_Database item8 = new Product_Database("Fifa 18", 1, "Games");
        Product_Database item9 = new Product_Database("Tekken 7", 1, "Games");
        Product_Database item10 = new Product_Database("Echo Dot", 2, "Overig");
        Product_Database item11 = new Product_Database("Rasberry Pi Camera", 5, "Computer");
        Product_Database item12 = new Product_Database("Rasberry Pi 3 Case", 5, "Computer");
        Product_Database item13 = new Product_Database("Grove Pi", 12, "Computer");
        Product_Database item14 = new Product_Database("Rasberry Pi 3 Model", 21, "Computer");
        Product_Database item15 = new Product_Database("Rasberry Pi Micro SD", 20, "Computer");
        Product_Database item16 = new Product_Database("Rasberry Pi Power Supply", 21, "Computer");
        Product_Database item17 = new Product_Database("Motion", 5, "Virtual Reality");
        Product_Database item18 = new Product_Database("Developer Mount", 5, "Virtual Reality");
        Product_Database item19 = new Product_Database("Micro-USB", 3, "Overig");
        Product_Database item20 = new Product_Database("VGA", 2, "Overig");
        Product_Database item21 = new Product_Database("DVI", 1, "Overig");
        Product_Database item22 = new Product_Database("WiFi", 1, "Overig");
        Product_Database item23 = new Product_Database("UTP", 2, "Overig");
        Product_Database item24 = new Product_Database("Adapter", 2, "Overig");
        Product_Database item25 = new Product_Database("Eye Tracker", 5, "Overig");
        Product_Database item26 = new Product_Database("Location Beacons", 5, "Overig");
        Product_Database item27 = new Product_Database("Low Energy Location Beacon", 10, "Overig");
        Product_Database item28 = new Product_Database("Dev Preview Location Beacon", 2, "Overig");
        Product_Database item29 = new Product_Database("Vive", 3, "Virtual Reality");
        Product_Database item30 = new Product_Database("Evolution", 5, "Virtual Reality");
        Product_Database item31 = new Product_Database("VRBox", 5, "Virtual Reality");
        Product_Database item32 = new Product_Database("AR Drone Power Edition", 2, "Drones");
        Product_Database item33 = new Product_Database("Rift", 3, "Virtual Reality");
        Product_Database item34 = new Product_Database("VR-PC", 1, "Computer");
        Product_Database item35 = new Product_Database("Taming Text", 1, "Boeken");
        Product_Database item36 = new Product_Database("iPhone and iPad Apps for Absolute Beginners", 1, "Boeken");
        Product_Database item37 = new Product_Database("Unlocking Android: A Developer's Guide", 1, "Boeken");
        Product_Database item38 = new Product_Database("Building Wireless Sensor Networks", 1, "Boeken");
        Product_Database item39 = new Product_Database("OpenGL ER 2.0 Programming Guide", 1, "Boeken");
        Product_Database item40 = new Product_Database("Data Mining with Rattle and R", 1, "Boeken");
        Product_Database item41 = new Product_Database("Fundamentals of Computer Graphics", 1, "Boeken");
        Product_Database item42 = new Product_Database("Head First: Java", 1, "Boeken");
        Product_Database item43 = new Product_Database("Head First: Design Patterns", 1, "Boeken");
        Product_Database item44 = new Product_Database("Programming with Python: Computer Vision", 1, "Boeken");
        Product_Database item45 = new Product_Database("Good Relationships: the spring Data Neo4J Guide Book", 1, "Boeken");
        Product_Database item46 = new Product_Database("Natural Language Processing with Python", 1, "Boeken");
        Product_Database item47 = new Product_Database("Data Mining: practical Machine learning Tools and Techniques", 1, "Boeken");
        Product_Database item48 = new Product_Database("Building Machine Learning Systems with Python", 1, "Boeken");
        Product_Database item49 = new Product_Database("Learning Android Game programming", 1, "Boeken");
        Product_Database item50 = new Product_Database("Mathematics for 3D Game Programming and Computer Graphics", 1, "Boeken");
        Product_Database item51 = new Product_Database("Practical computer vision with simplecv", 1, "Boeken");
        insertProduct(item1);
        insertProduct(item2);
        insertProduct(item3);
        insertProduct(item4);
        insertProduct(item5);
        insertProduct(item6);
        insertProduct(item7);
        insertProduct(item8);
        insertProduct(item9);
        insertProduct(item10);
        insertProduct(item11);
        insertProduct(item12);
        insertProduct(item13);
        insertProduct(item14);
        insertProduct(item15);
        insertProduct(item16);
        insertProduct(item17);
        insertProduct(item18);
        insertProduct(item19);
        insertProduct(item20);
        insertProduct(item21);
        insertProduct(item22);
        insertProduct(item23);
        insertProduct(item24);
        insertProduct(item25);
        insertProduct(item26);
        insertProduct(item27);
        insertProduct(item28);
        insertProduct(item29);
        insertProduct(item30);
        insertProduct(item31);
        insertProduct(item31);
        insertProduct(item32);
        insertProduct(item33);
        insertProduct(item34);
        insertProduct(item35);
        insertProduct(item36);
        insertProduct(item37);
        insertProduct(item38);
        insertProduct(item39);
        insertProduct(item40);
        insertProduct(item41);
        insertProduct(item42);
        insertProduct(item43);
        insertProduct(item44);
        insertProduct(item45);
        insertProduct(item46);
        insertProduct(item47);
        insertProduct(item48);
        insertProduct(item49);
        insertProduct(item50);
        insertProduct(item51);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
}