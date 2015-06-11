package net.hetic.hetweetic.Models;

/**
 * Created by chipowok on 10/06/15.
 */
public class Tweet {

    private String text;
    private String userName;
    private String profileImageUrl;


    public Tweet() {
    }

    public Tweet(String profileImageUrl, String text, String userName) {
        this.profileImageUrl = profileImageUrl;
        this.text = text;
        this.userName = userName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
