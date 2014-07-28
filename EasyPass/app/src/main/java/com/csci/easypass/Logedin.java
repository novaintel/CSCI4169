package com.csci.easypass;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.csci.easypass.library.MySQLiteHelper;
import com.csci.easypass.library.User;
import com.csci.easypass.library.Website;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 2014-07-14.
 */

public class Logedin extends Activity {
    TextView websiteUrl;
    TextView username;
    TextView password;
    MySQLiteHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logedin);

        websiteUrl=new TextView(this);
        websiteUrl=(TextView)findViewById(R.id.websiteurl_text);

        username=new TextView(this);
        username=(TextView)findViewById(R.id.username_text);

        password=new TextView(this);
        password=(TextView)findViewById(R.id.password_text);

        db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Books
        db.addUser(new User("James", "1234"));
        db.addWebsite(new Website("www","James", "1234"));
        db.addWebsite(new Website("google.ca","Moe", "password"));

        List<User> listUser = db.getAllUsers();
        List<Website> listWebsite = db.getAllWebsites();

        ArrayList<String> websiteurl = new ArrayList<String>();

        for(Website website:listWebsite){
            websiteurl.add(website.getWebsiteUrl());
        }

        db.getAllWebsites();
        db.getAllUsers();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                websiteurl );

        final ListView lv = (ListView) findViewById(R.id.show_accounts);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                String selectedFromList =(String) (lv.getItemAtPosition(myItemInt));
                websiteUrl.setText(selectedFromList);
                Website selectedWebsite = db.getWebSite(selectedFromList);
                username.setText(selectedWebsite.getUsername());
                password.setText(selectedWebsite.getPassword());
            }
        });

    }


}
