package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminLayout extends Activity {

    Button inventarisaanpassen;
    Button rechtenwijzigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlayout);

        inventarisaanpassen = findViewById(R.id.buttonadminproductlijst);
        rechtenwijzigen = findViewById(R.id.buttonrechtenaanpassen);

        inventarisaanpassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this, AdminAddProducts.class);
                startActivity(intent);
            }
        });
        rechtenwijzigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this, AdminVeranderRechten.class);
                startActivity(intent);
            }
        });

    }
}
