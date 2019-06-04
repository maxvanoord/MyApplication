package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Winkelmandje extends Activity {

    Button reserveren;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winkelmandje);

        reserveren = findViewById(R.id.buttonReserveren);

        reserveren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Winkelmandje.this, LeenActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = findViewById(R.id.listViewWinkelmand);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, helper.winkelmandje);
        listView.setAdapter(adapter);
    }
}
