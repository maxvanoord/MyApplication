package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProductScreen extends Activity{

    ImageView drone;
    ImageView shopCart;
    ImageView books;
    ImageView games;
    ImageView computers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productscreen);

        drone = findViewById(R.id.Drone);
        shopCart = findViewById(R.id.shopCartProductScreen);
        books = findViewById(R.id.booksimg);
        games = findViewById(R.id.gamesimg);
        computers = findViewById(R.id.computersimg);


        shopCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToShop = new Intent(ProductScreen.this, Winkelmandje.class);
                startActivity(goToShop);
            }
        });

        drone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent droneIntent = new Intent(ProductScreen.this, DroneScreen.class);
                startActivity(droneIntent);
            }
        });

        books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBooks = new Intent(ProductScreen.this, BooksScreen.class);
                startActivity(goToBooks);
            }
        });





    }
}