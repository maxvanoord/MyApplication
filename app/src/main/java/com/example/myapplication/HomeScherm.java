package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScherm extends Activity {

    Button productscherm;
    Button mijnproducten;
    Button uitloggen;
    Button contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescherm);

        productscherm = findViewById(R.id.buttonproductenlenen);
        productscherm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScherm.this, ProductScreen.class);
                startActivity(intent);
            }
        });


        mijnproducten = findViewById(R.id.buttonmijnproducten);
        mijnproducten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScherm.this, MijnProducten.class);
                startActivity(intent);
            }
        });


        uitloggen = findViewById(R.id.buttonuitloggenuser);
        uitloggen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScherm.this, MainActivity.class);
                startActivity(intent);

                Toast ingelogd = Toast.makeText(HomeScherm.this , "Uitgelogd" , Toast.LENGTH_SHORT);
                ingelogd.show();
            }
        });

        contact = findViewById(R.id.buttoncontacteninfo);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeScherm.this, contact.class);
                startActivity(intent);
            }
        });





    }

}
