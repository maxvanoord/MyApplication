package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MijnProducten extends Activity {

    ListView listView;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mijnproducten);

        listView = findViewById(R.id.listviewmijnproducten);
        helper = new DatabaseHelper(this);




    }

}
