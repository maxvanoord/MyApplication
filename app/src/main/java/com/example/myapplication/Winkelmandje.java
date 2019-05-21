package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Winkelmandje extends Activity {

    Button reserveren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.winkelmandje);

        reserveren = findViewById(R.id.buttonReserveren);

        reserveren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Winkelmandje.this, LeenActivity.class);
                startActivity(intent);
            }
        });
    }
}
