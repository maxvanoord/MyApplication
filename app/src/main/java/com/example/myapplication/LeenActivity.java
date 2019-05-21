package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LeenActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datakiezerbestelling);

        Button bevestigbutton = findViewById(R.id.leenbevestigingbutton);

        bevestigbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeenActivity.this, LeenBevestiging.class);
                startActivity(intent);
            }
        });



    }
}