package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DroneScreen extends Activity{

    ImageView shopping_cart;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dronescreen);

        helper = new DatabaseHelper(this);


        ListView listView = findViewById(R.id.listViewDrones);

        final ArrayList<String> droneList = new ArrayList<>();
        Cursor data = helper.GetProductByCat("Drones");

        if(data.getCount() == 0){
            Toast.makeText(this, "Er zijn geen producten beschikbaar in deze categorie",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                droneList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,droneList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                String item_name = item.toString();

                boolean stockOk = false;

                stockOk = helper.stockCheck(item_name);

                if (stockOk) {
                    Winkelmand_Database c = new Winkelmand_Database();
                    c.setName(item_name);
                    c.setAmount(1);
                    helper.insertWinkelmandje(c);
                    Toast.makeText(DroneScreen.this, item_name + " aan winkelmandje toegevoegd", Toast.LENGTH_SHORT).show();
                }
                else{Toast.makeText(DroneScreen.this, item_name + " is niet op voorraad", Toast.LENGTH_SHORT).show();}
            }
        });

        shopping_cart = findViewById(R.id.shopCartDronesScreen);

        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shoppingcartintent = new Intent(DroneScreen.this, Winkelmandje.class);
                startActivity(shoppingcartintent);
            }
        });
    }
}