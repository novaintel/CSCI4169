package com.csci.easypass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.csci.easypass.library.MySQLiteHelper;
import com.csci.easypass.library.Website;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 2014-07-14.
 */

public class Logedin extends Activity{
    TextView webSiteUrlText;
    TextView usernameText;
    TextView passwordText;

    ListView lv;

    MySQLiteHelper db;
    ArrayList<Integer> listWebIDs;
    ArrayList<String> webSiteUrl;
    Context context = this;
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

        refreshList();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                webSiteUrl );

        lv = (ListView) findViewById(R.id.show_accounts);

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

        addWebsiteButton.setBackgroundColor(Color.TRANSPARENT);

        addWebsiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }

    protected void refreshList(){

        List<Website> listWebsite = db.getAllWebsites();
        listWebIDs = new ArrayList<Integer>();

        if(webSiteUrl == null)
            webSiteUrl = new ArrayList<String>();
        else
            webSiteUrl.clear();

        for(Website website:listWebsite) {
            webSiteUrl.add(website.getWebsiteUrl());
            listWebIDs.add(website.getId());
        }
    }

    protected void showInputDialog() {

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(Logedin.this);
        View promptView = layoutInflater.inflate(R.layout.dialog_newwebsite, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Logedin.this);
        alertDialogBuilder.setView(promptView);

        final EditText websiteUrlEditText = (EditText) promptView
                .findViewById(R.id.addWebsiteUrlEditBox);
        final EditText websiteUserNameEditText = (EditText) promptView
                .findViewById(R.id.addWebsiteUsernameEditBox);
        final EditText websitePasswordEditText = (EditText) promptView
                .findViewById(R.id.addWebsitePasswordEditBox);
        // setup a dialog window
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        webSiteUrlText.setText(websiteUrlEditText.getText());
                        String websiteUrl = websiteUrlEditText.getText().toString();
                        String websiteUser = websiteUserNameEditText.getText().toString();
                        String websitePassword = websitePasswordEditText.getText().toString();

                        Website newWebsite = new Website(websiteUrl,websiteUser,websitePassword);

                        db.addWebsite(newWebsite);

                        refreshList();
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

    }



}
