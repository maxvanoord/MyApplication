package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Beheerder extends Activity {

    Button voorraad;
    Button reports;
    Button innemen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beheerder);

        voorraad = findViewById(R.id.buttonbeheerdervoorraad);
        reports = findViewById(R.id.buttonbeheerderreports);
        innemen = findViewById(R.id.buttonbeheerderinnemenproduct);

        voorraad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beheerder.this, VoorraadCheck.class);
                startActivity(intent);
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beheerder.this, ReportsBeheerder.class);
                startActivity(intent);
            }
        });

        innemen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beheerder.this, InnemenProducten.class);
                startActivity(intent);
            }
        });




    }
}
