package com.csci.easypass;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.csci.easypass.library.MySQLiteHelper;
import com.csci.easypass.library.User;
import com.csci.easypass.library.Website;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 2014-07-14.
 */

public class Logedin extends Activity implements Serializable{
    TextView webSiteUrlText;
    TextView usernameText;
    TextView passwordText;
    User user;

    ListView lv;

    MySQLiteHelper db;
    ArrayList<Integer> listWebIDs;
    ArrayList<String> webSiteUrl;
    ClipboardManager clipboard;
    Website currentWebsite;
    Context context = this;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logedin);

        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        user = (User)getIntent().getParcelableExtra("com.csci.easypass.library.User");

        webSiteUrlText=new TextView(this);
        webSiteUrlText=(TextView)findViewById(R.id.websiteUrl_TextBox);


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
                currentWebsite = db.getWebSite(listWebIDs.get((int)mylng));
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


        final Button settingsButton = (Button) findViewById(R.id.settingsLogin_button);

        settingsButton.setBackgroundColor(Color.TRANSPARENT);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), Settings.class);
                startActivity(nextScreen);
            }
        });

        final Button usernameButton = (Button) findViewById(R.id.userNameCopy);

        usernameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(currentWebsite != null)
                     clipboard.setText(currentWebsite.getUsername());
            }
        });


        final Button passwordButton = (Button) findViewById(R.id.copyPassword_button);

        passwordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(currentWebsite != null)
                clipboard.setText(currentWebsite.getPassword());
            }
        });
    }

    protected void refreshList(){

        List<Website> listWebsite = db.getAllWebsites(user.getId());
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

                        Website newWebsite = new Website(websiteUrl,websiteUser,websitePassword,user.getId());

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
