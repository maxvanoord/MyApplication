package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends Activity {

    EditText username;
    EditText email;
    EditText password;
    EditText repeatPassword;
    Button createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = findViewById(R.id.username_create);
        email = findViewById(R.id.email_create);
        password = findViewById(R.id.password_create);
        repeatPassword = findViewById(R.id.repeatpassword_create);
        createAccount = findViewById(R.id.button_createaccount);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

    }
}
