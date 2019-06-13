package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MijnProducten extends Activity {

    DatabaseHelper helper;
    TextView user;
    TextView producten;
    TextView ophaal;
    TextView terugbreng;
    String currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mijnproducten);

        helper = new DatabaseHelper(this);
        currentUser = DatabaseHelper.loginKeeper;

        user = findViewById(R.id.textViewHuurder);
        producten = findViewById(R.id.TextViewProducten);
        ophaal = findViewById(R.id.textViewOphaaldatum);
        terugbreng = findViewById(R.id.textViewTerugbreng);

        Cursor report = helper.GetReportsUser(currentUser);

        if (report.getCount() == 0) {
            Toast.makeText(this, "U heeft geen leningen op het moment", Toast.LENGTH_LONG).show();
        } else {
            while (report.moveToNext()) {
                {
                    user.setText(report.getString(1));
                    producten.setText(report.getString(2));
                    ophaal.setText(report.getString(3));
                    terugbreng.setText(report.getString(4));
                }
            }
        }
    }
}

