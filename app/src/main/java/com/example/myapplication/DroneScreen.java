package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DroneScreen extends Activity{

    ImageView shopping_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dronescreen);

        shopping_cart = findViewById(R.id.shoppingcart);

        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shoppingcartIntent = new Intent(DroneScreen.this, LeenBevestiging.class);
                startActivity(shoppingcartIntent);
            }
        });
    }
}