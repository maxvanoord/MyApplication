package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LeenBevestiging extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bevestigreservering);

        Button bevestigbestelling = findViewById(R.id.buttonbevestigbestelling);

        bevestigbestelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast geslaagd = Toast.makeText(LeenBevestiging.this , "Reservering is voltooid" , Toast.LENGTH_SHORT);
                geslaagd.show();

                Intent intent = new Intent(LeenBevestiging.this, ProductScreen.class);
                startActivity(intent);

            }
        });
    }
}