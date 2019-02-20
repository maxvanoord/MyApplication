package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    private Button button_register;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // variable button assigning to its id in xml file
        button_register = findViewById(R.id.register);
        button_login = findViewById(R.id.LoginButton);

        // build in method to assign a action to a button-press
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                openLoggedIn();
            }
        });
    }

    // Function
    public void openRegister(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        }

    public void openLoggedIn(){
        Intent intent2 = new Intent(this, ProductScreen.class);
        startActivity(intent2);
    }
    }
