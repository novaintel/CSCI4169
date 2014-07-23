package com.csci.easypass;

import com.csci.easypass.MainActivity.PlaceholderFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Login extends Activity {
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);

         startActivity(new Intent(this, Logedin.class));
         //setContentView(R.layout.logedin);
	        
	    }

}