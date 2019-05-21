package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{


    DatabaseHelper helper = new DatabaseHelper(this);

    public void onButtonClick(View v)

    {
        if (v.getId() == R.id.LoginButton) {

            EditText input_username = findViewById(R.id.StudentnummerText);
            EditText input_password = findViewById(R.id.WachtwoordText);

            String input_U = input_username.getText().toString();
            String input_P = input_password.getText().toString();

            Boolean checkData = helper.checkMatch(input_U, input_P);

            if(checkData){
                Intent i = new Intent(MainActivity.this, ProductScreen.class);
                startActivity(i);

                Toast ingelogd = Toast.makeText(MainActivity.this , "Succesvol ingelogd!" , Toast.LENGTH_SHORT);
                ingelogd.show();
            }

            else {
                Toast nieting = Toast.makeText(MainActivity.this , "Gebruikersnaam en wachtwoord komen niet overeen" , Toast.LENGTH_SHORT);
                nieting.show();
            }
        }
    }

    public void onRegisterClick(View v)
    {
        if(v.getId() == R.id.register)
        {
            Intent i = new Intent(MainActivity.this, Register.class);
            startActivity(i);
        }
    }

    public void onContactClick(View v) {
        if (v.getId() == R.id.ContactButton) {
            Intent x = new Intent(MainActivity.this, contact.class);
            startActivity(x);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
