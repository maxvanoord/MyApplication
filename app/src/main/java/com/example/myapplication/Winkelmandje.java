package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Winkelmandje extends Activity {

    Button reserveren;
    Button clear;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winkelmandje);

        reserveren = findViewById(R.id.buttonReserveren);
        clear = findViewById(R.id.clearWinkelmand);

        reserveren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Winkelmandje.this, LeenActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = findViewById(R.id.listViewWinkelmand);
        helper = new DatabaseHelper(this);

        final ArrayList<String> winkelmandList = new ArrayList<>();
        Cursor data = helper.GetWinkelmandProducts();

        if(data.getCount() == 0){
            Toast.makeText(this, "Winkelmandje is leeg",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                String item = data.getString(1);
                winkelmandList.add(item);
                DatabaseHelper.winkelmandItems += item + " / ";
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,winkelmandList);
                listView.setAdapter(listAdapter);
            }
        }

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.clearWinkelmandje();
                finish();
                startActivity(getIntent());
                Toast.makeText(Winkelmandje.this, "winkelmandje geleegd", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
