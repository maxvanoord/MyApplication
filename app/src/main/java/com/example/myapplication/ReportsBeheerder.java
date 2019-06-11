package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReportsBeheerder extends Activity {

    DatabaseHelper helper;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportsbeheerder);

        helper = new DatabaseHelper(this);
        listView = findViewById(R.id.listviewreports);

        HashMap<String, String> itemStock = new HashMap<>();

        Cursor allItems = helper.GetAllReports();

        if(allItems.getCount() == 0){
            Toast.makeText(this, "Voorraad is leeg!",Toast.LENGTH_LONG).show();
        }else{
            while(allItems.moveToNext()){
                itemStock.put(allItems.getString(1), allItems.getString(2));
            }
        }

        List<HashMap<String, String>> listItems = new ArrayList<>();

        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.layoutfile_voorraad,
                new String[]{"First Line", "Second Line"},
                new int[]{R.id.text1, R.id.text2});

        Iterator it = itemStock.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        listView.setAdapter(adapter);
    }

}
