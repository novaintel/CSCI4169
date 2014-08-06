package com.csci.easypass;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;

import com.csci.easypass.library.MySQLiteHelper;
import com.csci.easypass.library.User;


public class MainActivity extends Activity {
    MySQLiteHelper db;
    TextView usernameTextBox;
    TextView passwordTextBox;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnNextScreen = (Button) findViewById(R.id.create_account_btn);
        Button btnLogin = (Button)findViewById(R.id.login_btn);

        usernameTextBox = new EditText(this);
        usernameTextBox = (EditText) findViewById(R.id.firstpage_username);

        passwordTextBox = new EditText(this);
        passwordTextBox = (EditText) findViewById(R.id.firstpage_password);

        db = new MySQLiteHelper(this);
        
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
        	 
            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), Create_account.class);
                startActivity(nextScreen);}
        });
        
        btnLogin.setOnClickListener(new View.OnClickListener() {       	 
            public void onClick(View arg0) {
                //Starting a new Intent
                db.getAllUsers();
                User checkUser = db.getUser(usernameTextBox.getText().toString());
                db.getAllUsers();
                if(checkUser == null)
                    messgeBox("Error logging you in. Please check username and password");
                else if (checkUser.getPassword().equals(passwordTextBox.getText().toString())) {
                    Intent nextScreen = new Intent(getApplicationContext(), Logedin.class);
                    Bundle userBundle = new Bundle();
                    userBundle.putParcelable("com.csci.easypass.library.User", checkUser);
                    nextScreen.putExtras(userBundle);
                    startActivity(nextScreen);
                } else
                    messgeBox("Error logging you in. Please check username and password");
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
                    }
                });


        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
       
    }
    

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
