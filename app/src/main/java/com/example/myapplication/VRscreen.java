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

public class VRscreen extends Activity {

    ImageView shopCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vrscreen);

        DatabaseHelper helper = new DatabaseHelper(this);
        shopCart = findViewById(R.id.shopCartVRScreen);

        ListView listView = findViewById(R.id.listViewVR);

        final ArrayList<String> VRList = new ArrayList<>();
        Cursor data = helper.GetProductByCat("Virtual Reality");

        if(data.getCount() == 0){
            Toast.makeText(VRscreen.this, "Er zijn geen producten beschikbaar in deze categorie",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                VRList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,VRList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(VRscreen.this, "Toegevoegd aan winkelmandje",Toast.LENGTH_SHORT).show();
            }
        });

        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VRscreen.this, Winkelmandje.class);
                startActivity(intent);
            }
        });
    }
}
