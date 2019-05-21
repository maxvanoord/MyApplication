package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ComputersScreen extends Activity {

    ImageView shopCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.computerscreen);

        shopCart = findViewById(R.id.shopCartPcsScreen);

        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComputersScreen.this, Winkelmandje.class);
                startActivity(intent);
            }
        });
    }
}
