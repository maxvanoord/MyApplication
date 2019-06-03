package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductScreen extends Activity{

    ImageView drone;
    ImageView shopCart;
    ImageView books;
    ImageView games;
    ImageView computers;
    ImageView vr;
    TextView overig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productscreen);

        drone = findViewById(R.id.droneimg);
        shopCart = findViewById(R.id.shopCartGamesScreen);
        books = findViewById(R.id.booksimg);
        games = findViewById(R.id.gamesimg);
        computers = findViewById(R.id.computersimg);
        vr = findViewById(R.id.vrimg);
        overig = findViewById(R.id.textViewOverig);


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

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToGames = new Intent(ProductScreen.this, GamesScreen.class);
                startActivity(goToGames);
            }
        });

        computers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPcs = new Intent(ProductScreen.this, ComputersScreen.class);
                startActivity(goToPcs);
            }
        });

        vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToVR = new Intent(ProductScreen.this, VRscreen.class);
                startActivity(goToVR);
            }
        });

        overig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToOverig = new Intent(ProductScreen.this, OverigScreen.class);
                startActivity(goToOverig);
            }
        });


    }
}