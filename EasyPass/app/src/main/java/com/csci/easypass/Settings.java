package com.csci.easypass;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.UserManager;
import android.view.View;
import android.widget.Button;

/**
 * Created by james on 05/08/14.
 */
public class Settings extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        final Button addWebsiteButton = (Button) findViewById(R.id.addWebsiteSettings_button);

        addWebsiteButton.setBackgroundColor(Color.TRANSPARENT);

        addWebsiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishActivity(0);
            }
        });

        final Button settingsButton = (Button) findViewById(R.id.addWebsite_button);

        addWebsiteButton.setBackgroundColor(Color.TRANSPARENT);

        addWebsiteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });


        final Button logOutButton = (Button) findViewById(R.id.logout_button);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Settings.this,
                        MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

    }


}
