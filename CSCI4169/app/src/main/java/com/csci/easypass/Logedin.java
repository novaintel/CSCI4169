package com.csci.easypass;

import android.app.Activity;
import android.os.Bundle;

import com.csci.easypass.library.DB_Adapter;

/**
 * Created by James on 2014-07-14.
 */
public class Logedin extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        DB_Adapter db_adapter = new DB_Adapter(this);
    }

}
