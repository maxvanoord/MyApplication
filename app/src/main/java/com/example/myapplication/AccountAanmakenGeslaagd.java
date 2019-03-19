package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountAanmakenGeslaagd extends AppCompatActivity {

    Button Homescreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_aanmaken_geslaagd);

        Homescreen = findViewById(R.id.homescreen2);

        Homescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Homescreenintent = new Intent(AccountAanmakenGeslaagd.this, MainActivity.class);
                startActivity(Homescreenintent);
            }
        });
    }
}
