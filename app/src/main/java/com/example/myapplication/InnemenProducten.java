package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InnemenProducten extends Activity {

    DatabaseHelper helper;
    ListView listView;
    SimpleAdapter adapter;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.innemenproducten);

        helper = new DatabaseHelper(this);
        listView = findViewById(R.id.listViewInnemenProducten);

        HashMap<String, String> userItems = new HashMap<>();

        Cursor allItems = helper.GetAllReports();

        if(allItems.getCount() == 0){
            Toast.makeText(this, "Op het moment zijn er geen reports",Toast.LENGTH_LONG).show();
        }else{
            while(allItems.moveToNext()){
                userItems.put(allItems.getString(1), allItems.getString(2));
            }
        }

        List<HashMap<String, String>> listItems = new ArrayList<>();

        adapter = new SimpleAdapter(this, listItems, R.layout.layoutfile_voorraad,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});

        Iterator it = userItems.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);

//                test.setText(item_name);

                //helper.DeleteReport(item_name);

                adapter.notifyDataSetChanged();

                Toast ingenomen = Toast.makeText(InnemenProducten.this , "Producten zijn ingenomen" , Toast.LENGTH_SHORT);
                ingenomen.show();
            }
        });
    }

}

