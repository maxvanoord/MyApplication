package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class InnemenProducten extends Activity {

    DatabaseHelper helper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.innemenproducten);

        helper = new DatabaseHelper(this);
        listView = findViewById(R.id.listViewInnemenProducten);

        final ArrayList<String> productList = new ArrayList<>();
        Cursor data = helper.GetAllReports();

        if(data.getCount() == 0){
            Toast.makeText(InnemenProducten.this, "Er zijn geen producten die kunnen worden ingenomen",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                productList.add(data.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,productList);
                listView.setAdapter(listAdapter);
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                String item_name = item.toString();

                helper.DeleteReport(item_name);

                finish();
                startActivity(getIntent());

                Toast.makeText(InnemenProducten.this, item_name + " zijn ingenomen" ,Toast.LENGTH_SHORT).show();
            }
        });

    }

}

