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

public class GamesScreen extends Activity {

    ImageView shopCart;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamesscreen);

        helper = new DatabaseHelper(this);
        shopCart = findViewById(R.id.shopCartGamesScreen);

        ListView listView = findViewById(R.id.listViewGames);

        final ArrayList<String> droneList = new ArrayList<>();
        Cursor data = helper.GetProductByCat("Games");

        if(data.getCount() == 0){
            Toast.makeText(GamesScreen.this, "Er zijn geen producten beschikbaar in deze categorie",Toast.LENGTH_LONG).show();
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

                Winkelmand_Database c = new Winkelmand_Database();
                c.setName(item_name);
                c.setAmount(1);
                helper.insertWinkelmandje(c);
                Toast.makeText(GamesScreen.this, item_name + " aan winkelmandje toegevoegd" ,Toast.LENGTH_SHORT).show();
            }
        });

        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesScreen.this, Winkelmandje.class);
                startActivity(intent);
            }
        });
    }
}