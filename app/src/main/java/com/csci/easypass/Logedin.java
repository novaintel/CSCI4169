package com.csci.easypass;

import android.app.Activity;
import android.os.Bundle;

import com.csci.easypass.library.MySQLiteHelper;
import com.csci.easypass.library.User;

import java.util.List;

/**
 * Created by James on 2014-07-14.
 */
public class Logedin extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logedin);

        MySQLiteHelper db = new MySQLiteHelper(this);

        /**
         * CRUD Operations
         * */
        // add Books
        db.addUser(new User("James", "1234"));

        List<User> listUser = db.getAllUsers();

        db.deleteUser(listUser.get(0));

        db.getAllUsers();

    }

}
