package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminDeleteProducts extends AppCompatActivity {

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admindeleteproducts);

        helper = new DatabaseHelper(this);


        ListView listView = findViewById(R.id.listViewProductenVerwijderen);

        final ArrayList<String> allList = new ArrayList<>();
        Cursor data = helper.GetAllProducts();

        if (data.getCount() == 0) {
            Toast.makeText(this, "Er zijn geen producten gevonden", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                allList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                String item_name = item.toString();
                helper.DeleteProduct(item_name);
                Toast.makeText(AdminDeleteProducts.this, "Product verwijderd", Toast.LENGTH_LONG).show();
            }
        });
    }
}

