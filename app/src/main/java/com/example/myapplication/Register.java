package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
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
            EditText username = findViewById(R.id.username_create); // hier haal je de opgegeven data uit de tekstblokken
            EditText email = findViewById(R.id.email_create);
            EditText pass1 = findViewById(R.id.password_create);
            EditText pass2 = findViewById(R.id.repeatpassword_create);

            String usernamestr = username.getText().toString();     // hier maak je van die data strings
            String emailstr = email.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            Boolean x = true;
            Boolean y = true;

            Boolean checkE = helper.checkMail(emailstr);
            Boolean checkU = helper.checkUsername(usernamestr);

            if (usernamestr.equals("")||emailstr.equals("")||pass1str.equals("")){
                Toast leeg = Toast.makeText(Register.this, "Verplichte velden mogen niet leeg zijn", Toast.LENGTH_SHORT);
                leeg.show();
                x = false;
            }

            if (!pass1str.equals(pass2str))                          // hier check je of de 2 passwords hetzelfde zijn
            {
                Toast pass = Toast.makeText(Register.this , "Wachtwoorden komen niet overeen" , Toast.LENGTH_SHORT); // popup als ze niet hetzelfde zijn
                pass.show();
                y = false;
            }


            if(x&&y) {
                if (checkE&&checkU) {

                    Contact_Database c = new Contact_Database();        // hier word een nieuw object aangemaakt en toegevoegd aan de db
                    c.setUsername(usernamestr);
                    c.setEmail(emailstr);
                    c.setPassword(pass1str);
                    helper.insertContact(c);

                    Toast correct = Toast.makeText(Register.this, "Account is aangemaakt", Toast.LENGTH_SHORT);
                    correct.show();

                    Intent xy = new Intent(Register.this, MainActivity.class);
                    startActivity(xy);
                }

                else{
                    Toast gebruik = Toast.makeText(Register.this , "Gegevens zijn al in gebruik" , Toast.LENGTH_SHORT); // popup als ze niet hetzelfde zijn
                    gebruik.show();
                }
            }

        }


    }
}