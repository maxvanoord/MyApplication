package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminLayout extends Activity {

    DatabaseHelper helper;

    Button inventarisaanpassen;
    Button rechtenwijzigen;
    Button voorraadreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlayout);

        helper = new DatabaseHelper(this);

        inventarisaanpassen = findViewById(R.id.buttonadminproductlijst);
        rechtenwijzigen = findViewById(R.id.buttonrechtenaanpassen);
        voorraadreset = findViewById(R.id.buttonVoorraadreset);

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
        voorraadreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.insertAllProducts();
                Toast.makeText(AdminLayout.this, "Voorraad is geladen", Toast.LENGTH_LONG).show();
            }
        });

    }
}
