package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BestellingGeslaagd extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bestellinggeslaagd);


        Button buttonhome1 = findViewById(R.id.buttonhome);

        buttonhome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbevestig = new Intent(BestellingGeslaagd.this, ProductScreen.class);
                startActivity(intentbevestig);
            }
        });
    }
}