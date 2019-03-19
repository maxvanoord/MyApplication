package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LeenBevestiging extends AppCompatActivity {

    TextView textproduct = findViewById(R.id.productopoverzicht);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leenbevestiging);

        //String product = LeenActivity.spinner.getSelectedItem().toString();

        //textproduct.setText(product);


        Button bevestigbestelling = findViewById(R.id.buttonbevestigbestelling);

        bevestigbestelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeenBevestiging.this, BestellingGeslaagd.class);
                startActivity(intent);
            }
        });
    }
}