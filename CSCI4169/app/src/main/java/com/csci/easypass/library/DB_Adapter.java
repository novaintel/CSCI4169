package com.csci.easypass.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DB_Adapter
{
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

    //Database Version
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
    public DB_Adapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        insertWebsite("website", "james", "1234");
        getAllContacts();
    }


    private class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            open();
            db.execSQL(user_TABLE_CREATE);
            db.execSQL(password_TABLE_CREATE);
            close();
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                                  int newVersion)
            {
                Log.w(TAG, "Upgrading database from version " + oldVersion
                        + " to "
                        + newVersion + ", which will destroy all old data");
                db.execSQL("DROP TABLE IF EXISTS student");
                onCreate(db);
            }
    }

    //---opens the database---
    public DB_Adapter open() throws SQLException
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close()
    {
        DBHelper.close();
    }



    //---insert an User into the database---
    public long insertUser(String username, String password)
    {
        open();
        String sql = "SELECT * FROM user_table WHERE KEY_user_name = '" + username + "'";
        Cursor data = db.rawQuery(sql, null);
        if (data.moveToFirst()) {
            return -1;
        } else {
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_user_name, username);
            initialValues.put(KEY_user_password, password);
            long result = db.insert(USER_DB_TABLE, null, initialValues);
            close();
            return result;
        }

    }

    public long insertWebsite(String url, String username, String password)
    {
        open();
        String sql = "SELECT * FROM password_table WHERE KEY_website_url = '" + url + "'";
        Cursor data = db.rawQuery(sql, null);
        if (data.moveToFirst()) {
            return -1;
        } else {
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_website_url, url);
            initialValues.put(KEY_website_username, username);
            initialValues.put(KEY_website_user_pass, password);
            long result = db.insert(USER_DB_TABLE, null, initialValues);
            close();
            return result;
        }
    }

    public boolean logUserIn(String username, String password){
        String sql = "SELECT * FROM user_table WHERE KEY_user_name = '" + username + "'";
        Cursor data = db.rawQuery(sql, null);
        if (data.moveToFirst()) {
            sql = "SELECT password FROM user_table WHERE KEY_user_name = '" + username + "'";
            data = db.rawQuery(sql, null);
            if (data.moveToFirst()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Getting All Contacts
    public List<String> getAllContacts() {
        List<String> contactList = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM password_table";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                contactList.add(cursor.getString(0));
                Log.d("Name: ", cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    public Cursor searchUser(String name){
        String sql = "SELECT * FROM student_table WHERE "+ KEY_user_name + " LIKE '%" + name + "%'";
        return db.rawQuery(sql, null);
    }

    //---deletes a particular student---
    public boolean deleteUser(String name)
    {
        return db.delete(USER_DB_TABLE, KEY_user_name +
                "=" + KEY_user_name, null) > 0;
    }
}