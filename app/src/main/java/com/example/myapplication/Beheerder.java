package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Beheerder extends Activity {

    Button buttonvoorraad = findViewById(R.id.buttonbeheerderproducten);
    Button buttonreports = findViewById(R.id.buttonbeheerderreports);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beheerder);

        buttonvoorraad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentvoorraad = new Intent(Beheerder.this, BeheerderOverzicht.class);
                startActivity(intentvoorraad);
            }
        });

        buttonreports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentreports = new Intent(Beheerder.this, ReportsBeheerder.class);
                startActivity(intentreports);
            }
        });

    }
}
