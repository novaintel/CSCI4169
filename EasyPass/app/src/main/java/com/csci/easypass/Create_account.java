package com.csci.easypass;

import com.csci.easypass.MainActivity.PlaceholderFragment;
import com.csci.easypass.library.MySQLiteHelper;
import com.csci.easypass.library.User;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Create_account extends Activity {

    TextView passwordAgainTextBox;
    TextView usernameTextBox;
    TextView passwordTextBox;
    final Context context = this;
    MySQLiteHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        usernameTextBox = new EditText(this);
        usernameTextBox = (EditText) findViewById(R.id.create_username);

        passwordTextBox = new EditText(this);
        passwordTextBox = (EditText) findViewById(R.id.create_password);

        passwordAgainTextBox = new EditText(this);
        passwordAgainTextBox = (EditText) findViewById(R.id.create_reenter_password);

        db = new MySQLiteHelper(this);

        Button createAccButton = (Button) findViewById(R.id.sign_up);

        createAccButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                String username = usernameTextBox.getText().toString();
                String password = passwordTextBox.getText().toString();
                String passwordAgain = passwordAgainTextBox.getText().toString();
                if(username.equals("") || username.equals(" ")){
                    messgeBox("Please enter a username");
                }

                else if(password.equals("") || password.equals(" ")){
                    messgeBox("Please enter a password");
                }
                else if(passwordAgain.equals("") || passwordAgain.equals(" ")){
                    messgeBox("Please enter your password again");
                }
                else if(password.equals(passwordAgain)){
                    messgeBox("Passwords do not match!");
                }
                else if(password.equals(passwordAgain)){
                    db.addUser(new User(username, password));
                    Intent nextScreen = new Intent(getApplicationContext(), Logedin.class);
                    startActivity(nextScreen);
                }


            }

        });

    }

    private void messgeBox(String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Create Account Error");

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        Create_account.this.finish();
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}