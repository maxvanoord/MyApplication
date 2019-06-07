package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LeenBevestiging extends AppCompatActivity {

    DatabaseHelper helper;
    TextView datum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bevestigreservering);

        Intent intent = getIntent();
        String data1 = intent.getStringExtra("DATUM");
        datum = findViewById(R.id.TextViewDatumDisplayer);
        datum.setText(data1);

        Button bevestigbestelling = findViewById(R.id.buttonbevestigbestelling);

        ListView listView = findViewById(R.id.ListViewOverzichtBestelling);
        helper = new DatabaseHelper(this);

        final ArrayList<String> overzichtList = new ArrayList<>();
        Cursor data = helper.GetWinkelmandProducts();

        if(data.getCount() == 0){
            Toast.makeText(this, "Selecteer tenminste 1 product om te lenen!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                overzichtList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,overzichtList);
                listView.setAdapter(listAdapter);
            }
        }



        bevestigbestelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast geslaagd = Toast.makeText(LeenBevestiging.this , "Reservering is voltooid" , Toast.LENGTH_SHORT);
                geslaagd.show();
                Intent bevestiging = new Intent(LeenBevestiging.this, ProductScreen.class);
                startActivity(bevestiging);

            }
        });
    }
}