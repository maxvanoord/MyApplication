package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProductScreen extends Activity{

    ImageView drone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productscreen);

        drone = findViewById(R.id.Drone);

        drone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent droneIntent = new Intent(ProductScreen.this, DroneScreen.class);
                startActivity(droneIntent);
            }
        });


    }
}