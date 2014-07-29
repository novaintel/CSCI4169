package com.csci.easypass;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
    TextView webSiteUrlText;
    TextView usernameText;
    TextView passwordText;
    MySQLiteHelper db;
    ArrayList<Integer> listWebIDs;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logedin);

        webSiteUrlText=new TextView(this);
        webSiteUrlText=(TextView)findViewById(R.id.websiteUrl_TextBox);

        usernameText=new TextView(this);
        usernameText=(TextView)findViewById(R.id.username_TextBox);

        passwordText=new TextView(this);
        passwordText=(TextView)findViewById(R.id.password_TextBox);

        db = new MySQLiteHelper(this);
        List<Website> listWebsite = db.getAllWebsites();
        listWebIDs = new ArrayList<Integer>();

        final ArrayList<String> webSiteUrl = new ArrayList<String>();

        for(Website website:listWebsite){
            webSiteUrl.add(website.getWebsiteUrl());
            listWebIDs.add(website.getId());
        }

        db.getAllWebsites();
        db.getAllUsers();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                webSiteUrl );

        final ListView lv = (ListView) findViewById(R.id.show_accounts);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
                Website currentWebsite = db.getWebSite(listWebIDs.get((int)mylng));
                usernameText.setText(currentWebsite.getUsername());
                passwordText.setText(currentWebsite.getPassword());
                webSiteUrlText.setText(currentWebsite.getWebsiteUrl());
            }
        });

        final Button addWebsiteButton = (Button) findViewById(R.id.addWebsite_button);

        addWebsiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

    }


}
