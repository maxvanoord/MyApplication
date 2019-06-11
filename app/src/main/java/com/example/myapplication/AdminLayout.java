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
    Button inventarisverwijderen;
    Button voorraadreset;
    Button voorraad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlayout);

        helper = new DatabaseHelper(this);

        inventarisaanpassen = findViewById(R.id.buttonadminproductlijst);
        inventarisverwijderen = findViewById(R.id.buttonproductenverwijderen);
        voorraadreset = findViewById(R.id.buttonVoorraadreset);
        voorraad = findViewById(R.id.buttonbeheerderproducten2);


        inventarisaanpassen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this, AdminAddProducts.class);
                startActivity(intent);
            }
        });
        inventarisverwijderen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this, admin_all_products.class);
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
        voorraad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLayout.this, VoorraadCheck.class);
                startActivity(intent);
            }
        });

    }
}
