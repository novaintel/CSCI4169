package com.csci.easypass.library;

/**
 * Created by James on 2014-07-23.
 */
public class Website {

    private int id;
    private String websiteUrl;
    private String username;
    private String password;
    private int userId;

    public Website() {
    }

    public Website(String websiteUrl, String username, String password, int userId) {
        this.websiteUrl = websiteUrl;
        this.username = username;
        this.password = password;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", websiteUrl='" + websiteUrl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userId=" + userId +
                '}';
    }
}
