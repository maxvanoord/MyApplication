package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdminAddProducts extends Activity implements OnItemSelectedListener {

    DatabaseHelper helper = new DatabaseHelper(this);

    public void onVoegtoeClick(View v){

        if (v.getId() == R.id.buttonVoegProductToe)
        {

            EditText input_name = findViewById(R.id.tekstInputNaam);
            EditText input_stock = findViewById(R.id.tekstInputVoorraad);
            Spinner spinner = findViewById(R.id.choose_categorie);

            String name_str = input_name.getText().toString();
            String cat_str = spinner.getSelectedItem().toString();
            String stock_str = input_stock.getText().toString();


            if (name_str.equals("")) {
                Toast leeg2 = Toast.makeText(AdminAddProducts.this, "Productnaam mag niet leeg zijn", Toast.LENGTH_SHORT);
                leeg2.show();
            }
            else {
                try {
                    int stock_int = Integer.parseInt(stock_str);

                    Product_Database c = new Product_Database(name_str, stock_int, cat_str);        // hier word een nieuw object aangemaakt en toegevoegd aan de db
                    helper.insertProduct(c);
                    Toast correct = Toast.makeText(AdminAddProducts.this, "Product toegevoegd", Toast.LENGTH_SHORT);
                    correct.show();
                } catch (NumberFormatException e) {
                    Toast vrdfout = Toast.makeText(AdminAddProducts.this, "Veld 'voorraad' moet een getal zijn", Toast.LENGTH_SHORT);
                    vrdfout.show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminaddproducts);

        Spinner spinner = findViewById(R.id.choose_categorie);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}