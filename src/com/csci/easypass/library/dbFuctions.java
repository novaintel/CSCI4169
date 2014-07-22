package com.csci.easypass.library;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by James on 2014-07-21.
 */
public class dbFuctions {
    //Database Name
    private static final String DATABASE_NAME = "_appdev";

    //Student table Name
    private static final String USER_DB_TABLE = "user_table";

    //SQLite names of each field of the user table
    public static final String KEY_user_name = "user_name";
    public static final String KEY_user_password = "user_password";

    //SQLite names of each field of the password table
    public static final String KEY_website_url = "website_url";
    public static final String KEY_website_username = "website_username";
    public static final String KEY_website_user_pass = "website_pass";

    private static final String TAG = "DBAdapter";

    //Database version
    private static final int DATABASE_VERSION = 1;

    //SQL statement to create user table as a string. Used to create the table in OnCreate()
    private static final String user_TABLE_CREATE =
            "create table user_table (SID integer primary key autoincrement, "
            + KEY_user_name + " text not null, " + KEY_user_password + " text not null);";

    private static final String password_TABLE_CREATE =
            "create table password_table (SID integer primary key autoincrement, "
            + KEY_website_url + " text not null, " + KEY_website_username + " text not null"
            + KEY_website_user_pass + " text not null);";

    private final Context context;

    //Declaring Database objects
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    //DB_Adapter constructor
    public dbFuctions(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
}
