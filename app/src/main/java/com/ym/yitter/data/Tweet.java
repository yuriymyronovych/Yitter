package com.ym.yitter.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Yuriy Myronovych on 20/08/2015.
 */
public class Tweet {
    @SerializedName("text") private String text;
    @SerializedName("created_at") private Date createdAt;
    @SerializedName("user") private User user;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
