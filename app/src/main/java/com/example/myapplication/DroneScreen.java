package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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

        ListView listView = findViewById(R.id.listView);
        helper = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = helper.GetProductByCat("");
        if(data.getCount() == 0){
            Toast.makeText(this, "Er zijn geen producten beschikbaar in deze categorie",Toast.LENGTH_LONG).show();
        }else{
            int i = 0;
            while(data.moveToNext()){
                Product_Database product = new Product_Database(data.getString(1), data.getInt(2), data.getString(3));
                theList.add(i, data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
                i ++;
            }
        }

        shopping_cart = findViewById(R.id.shopCartDronesScreen);

        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shoppingcartintent = new Intent(DroneScreen.this, LeenBevestiging.class);
                startActivity(shoppingcartintent);
            }
        });
    }
}