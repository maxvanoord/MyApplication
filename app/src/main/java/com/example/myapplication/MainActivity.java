package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    TextView button_register;
    Button button_login;
    Button button_contact;
    EditText input_username;
    EditText input_password;
    DatabaseHelper helper = new DatabaseHelper(this);

    public void onButtonClick(View v)
    {
        if (v.getId() == R.id.LoginButton)
        {
            EditText a = findViewById(R.id.username_create);
            String str = a.getText().toString();
            EditText b = findViewById(R.id.password_create);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);


            if(pass.equals(password))
            {
                Intent i = new Intent(MainActivity.this, ProductScreen.class);
                i.putExtra("Username",str);
                startActivity(i);
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this , "Username and password don't match" , Toast.LENGTH_SHORT);
                temp.show();
            }

        }
        if(v.getId() == R.id.button_createaccount)
        {
            Intent i = new Intent(MainActivity.this, Register.class);

            startActivity(i);
        }
    }

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
