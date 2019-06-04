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

public class BooksScreen extends Activity {

    ImageView shopCart;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booksscreen);

        helper = new DatabaseHelper(this);

        ListView listView = findViewById(R.id.listViewBooks);

        final ArrayList<String> booksList = new ArrayList<>();
        Cursor data = helper.GetProductByCat("Boeken");

        if(data.getCount() == 0){
            Toast.makeText(this, "Er zijn geen producten beschikbaar in deze categorie",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                booksList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,booksList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                String item_string = item.toString();
                helper.aanWinkelmandje(item_string);
                Toast.makeText(BooksScreen.this, item_string ,Toast.LENGTH_SHORT).show();
            }
        });



        shopCart = findViewById(R.id.shopCartBooksScreen);
        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BooksScreen.this, Winkelmandje.class);
                startActivity(intent);
            }
        });
    }
}