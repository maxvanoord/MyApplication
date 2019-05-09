package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {

    EditText username;
    EditText email;
    EditText password;
    EditText repeatPassword;
    Button createAccount;

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = findViewById(R.id.username_create);
        email = findViewById(R.id.email_create);
        password = findViewById(R.id.password_create);
        repeatPassword = findViewById(R.id.repeatpassword_create);
        createAccount = findViewById(R.id.button_createaccount);


    }
    public void onRegisterClick(View v){

        if(v.getId()== R.id.button_createaccount)
        {
            EditText username = findViewById(R.id.username_create);
            EditText email = findViewById(R.id.email_create);
            EditText pass1 = findViewById(R.id.password_create);
            EditText pass2 = findViewById(R.id.repeatpassword_create);

            String usernamestr = username.getText().toString();
            String emailstr = email.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str))
            {
                Toast pass = Toast.makeText(Register.this , "Passwords don't match" , Toast.LENGTH_SHORT);
                pass.show();
            }
            else {
                Contact_Database c = new Contact_Database();
                c.setUsername(usernamestr);
                c.setEmail(emailstr);
                c.setPassword(pass1str);

                helper.insertContact(c);
            }

        }


    }
}