package com.csci.easypass;

import com.csci.easypass.MainActivity.PlaceholderFragment;
import com.csci.easypass.library.MySQLiteHelper;
import com.csci.easypass.library.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Create_account extends Activity {

    TextView passwordAgainTextBox;
    TextView usernameTextBox;
    TextView passwordTextBox;
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

                String username = (String)usernameTextBox.getText();
                String password = (String)passwordTextBox.getText();
                String passwordAgain = (String)passwordAgainTextBox.getText();
                if(username.equals("") || username.equals(" ")){
                    return;
                }
                if(password.equals("") || password.equals(" ")){
                    return;
                }
                if(passwordAgain.equals("") || passwordAgain.equals(" ")){
                    return;
                }
                if(password.equals(passwordAgain)){
                    db.addUser(new User(username, password));
                }

            }

        });

    }

}