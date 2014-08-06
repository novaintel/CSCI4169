package com.csci.easypass.library;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by James on 2014-07-23.
 */
public class User implements Parcelable {

    private int id;
    private String password;
    private String username;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel parcel) {
            User user = new User();
            user.setId(parcel.readInt());
            user.setUsername(parcel.readString());
            user.setPassword(parcel.readString());
            return user;
        }

        @Override
        public User[] newArray(int i) {
            return new User[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(password);
    }
}
