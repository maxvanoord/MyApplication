package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAddProducts extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    public void onVoegtoeClick(View v){

        if (v.getId() == R.id.buttonVoegProductToe) {

            EditText input_name = findViewById(R.id.tekstInputNaam);
            EditText input_stock = findViewById(R.id.tekstInputVoorraad);
            EditText input_catg = findViewById(R.id.tekstInputCategorie);

            String name_str = input_name.getText().toString();
            String stock_str = input_stock.getText().toString();
            int stock_int = Integer.parseInt(stock_str);
            String cat_str = input_catg.getText().toString();

            Product_Database c = new Product_Database();        // hier word een nieuw object aangemaakt en toegevoegd aan de db
            c.setName(name_str);
            c.setStock(stock_int);
            c.setCategorie(cat_str);
            helper.insertProduct(c);

            Toast correct = Toast.makeText(AdminAddProducts.this, "Product is toegevoegd", Toast.LENGTH_SHORT);
            correct.show();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminaddproducts);


    }
}