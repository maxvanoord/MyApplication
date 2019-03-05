package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView button_register;
    Button button_login;
    Button button_contact;
    EditText input_username;
    EditText input_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variable button assigning to its id in xml file
        button_register = findViewById(R.id.register);
        button_login = findViewById(R.id.LoginButton);
        input_username = findViewById(R.id.StudentnummerText);
        input_password = findViewById(R.id.WachtwoordText);
        button_contact = findViewById(R.id.ContactButton);

        // build in method to assign a action to a button-press
        button_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Activity_contactintent = new Intent(MainActivity.this, contact.class);
                startActivity(Activity_contactintent);
            }
        });
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registerintent = new Intent(MainActivity.this, Register.class);
                startActivity(Registerintent);
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ProductScreenintent = new Intent(MainActivity.this, ProductScreen.class);
                startActivity(ProductScreenintent);
            }
        });
    }
}
